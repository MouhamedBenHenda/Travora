# TRAVORA — Product & Technical Documentation

**Version:** 1.0.0
**Status:** Initial development
**Backend:** Java 17, Spring Boot 3.5.6
**Database:** MySQL 8
**Security:** Spring Security + JWT
**Repository:** [https://github.com/MouhamedBenHenda/Travora](https://github.com/MouhamedBenHenda/Travora)

---

# 00. Executive Summary

## 0.1 Product Overview

Travora is a travel, events, and social discovery platform designed to help people discover activities, create events, meet other travelers, and participate in shared experiences.

Users can explore events, request participation, communicate through group chats, and connect with people who share similar interests.

## 0.2 Product Objectives

- Make discovering local activities easier.
- Allow users to create and manage events.
- Support participation requests and participant management.
- Provide secure authentication and authorization.
- Enable group communication between participants.
- Support location-based event discovery.
- Build a maintainable and scalable software architecture.

## 0.3 Current Technology Stack

| Component       | Technology                  |
| --------------- | --------------------------- |
| Backend         | Spring Boot 3.5.6           |
| Language        | Java 17                     |
| Database        | MySQL 8                     |
| ORM             | Spring Data JPA / Hibernate |
| Security        | Spring Security + JWT       |
| Build tool      | Maven                       |
| Version control | Git + GitHub                |
| Frontend        | To be selected / documented |
| Hosting         | To be selected              |

## 0.4 Project Status

The repository contains initial backend implementations for authentication, users, events, categories, participation, and group messaging.

Production readiness has not yet been verified. Testing, authorization rules, database migrations, secret management, deployment, monitoring, and security hardening must be validated before release.

---

# 01. Product Research

## 1.1 Vision

Become a platform where people can discover destinations and activities, organize shared experiences, and connect with other travelers.

## 1.2 Problem Statement

Travelers and activity enthusiasts may struggle to find compatible people, discover relevant local events, and coordinate participation in group activities.

Travora aims to centralize event discovery, participation management, and communication.

## 1.3 Target Users

- Solo travelers looking for companions.
- Students seeking social activities.
- Local activity organizers.
- Travelers interested in cultural and outdoor experiences.
- People looking to discover activities near their location.

## 1.4 Market Research

Research should validate:

- Demand for group travel and local activity discovery.
- User expectations for trust, safety, and participant verification.
- Preferred event categories and pricing models.
- Adoption of location-based discovery.
- Opportunities for differentiation.

Potential research methods include competitor analysis, interviews, surveys, and prototype testing.

## 1.5 Competitors

Research products such as:

- Meetup — community events and groups.
- Couchsurfing — travel communities and social connections.
- Eventbrite — event discovery and registration.
- Nomadtable — social discovery and shared experiences.

These are research references, not claims that Travora has feature parity with them.

## 1.6 User Journey

1. A visitor discovers Travora.
2. The visitor creates an account or signs in.
3. The user explores available events.
4. The user opens an event and reviews its details.
5. The user submits a participation request.
6. The organizer reviews the request.
7. Upon acceptance, the participant accesses the relevant group communication features.
8. Participants attend the event and may discover other activities.

---

# 02. Business Analysis

## 2.1 Actors

| Actor              | Responsibilities                             |
| ------------------ | -------------------------------------------- |
| Visitor            | Browse publicly available events             |
| Authenticated user | Manage profile and request participation     |
| Event organizer    | Create events and manage participation       |
| Administrator      | Moderate users, events, and reported content |
| System             | Enforce rules, security, and notifications   |

The administrator role and visitor permissions must be explicitly implemented and verified.

## 2.2 Functional Requirements

**FR-01 — Authentication**

- Users can register and sign in.
- Passwords must be securely hashed.
- Protected endpoints require valid authentication.
- JWT expiration and invalid-token behavior must be enforced.

**FR-02 — User Management**

- Users can view and update permitted profile information.
- Users cannot modify another user's protected data without authorization.

**FR-03 — Event Management**

- Authorized users can create events.
- Organizers can update or cancel their own events.
- Users can retrieve event details and browse events.
- Events can include dates, location, description, category, and capacity.

**FR-04 — Categories**

- Events can be associated with categories.
- Categories can be retrieved and managed by authorized actors.

**FR-05 — Participation**

- Users can request to participate in an event.
- Organizers can accept or reject requests.
- The system prevents duplicate requests and capacity violations.

**FR-06 — Group Chat**

- Authorized participants can access their group conversations.
- Messages are persisted and associated with their sender and group.
- Users cannot access unrelated private groups.

**FR-07 — Location Discovery**

- Events can store latitude and longitude.
- Users can discover events based on geographic proximity.

## 2.3 Non-Functional Requirements

- **Security:** validate every protected operation on the server.
- **Performance:** paginate event listings and avoid unnecessary database queries.
- **Reliability:** use transactions for operations involving multiple database changes.
- **Maintainability:** separate controllers, services, repositories, DTOs, and domain models.
- **Observability:** log relevant failures without exposing passwords or tokens.
- **Accessibility:** provide accessible interfaces and clear validation messages.
- **Scalability:** keep external integrations and asynchronous workloads modular.

Numeric performance targets should be defined after establishing expected traffic and measuring a baseline.

## 2.4 Business Rules

1. A user must be authenticated to request participation.
2. Only the event organizer or an authorized administrator may manage an event.
3. An event cannot accept more participants than its configured capacity.
4. A user must not have multiple active participation requests for the same event.
5. Only accepted participants and authorized organizers may access event-specific group chats.
6. A cancelled event must not accept new participation requests.
7. Users may modify only resources for which they have the necessary permissions.
8. Event start and end dates must be valid.
9. Latitude must be between -90 and 90; longitude must be between -180 and 180.

## 2.5 Use Cases

| ID    | Use case                  | Primary actor          |
| ----- | ------------------------- | ---------------------- |
| UC-01 | Register and sign in      | Visitor                |
| UC-02 | Browse events             | Visitor / User         |
| UC-03 | Create an event           | Organizer              |
| UC-04 | Update an event           | Organizer              |
| UC-05 | Request participation     | User                   |
| UC-06 | Approve participation     | Organizer              |
| UC-07 | Send a group message      | Authorized participant |
| UC-08 | Manage categories         | Administrator          |
| UC-09 | Moderate reported content | Administrator          |

## 2.6 Acceptance Criteria

Example: requesting participation.

- Given an authenticated user and an open event with available capacity, when the user requests participation, a request is created.
- If the same user submits a duplicate request, the system rejects it or returns the existing request according to the API contract.
- If the event is full or cancelled, the request is rejected.
- If the user is unauthenticated, the endpoint returns HTTP 401.
- If the user is authenticated but unauthorized, the endpoint returns HTTP 403 where appropriate.

---

# 03. Domain Design

## 3.1 Core Entities

The current codebase includes the following model concepts:

- User
- Event
- Category
- Participation
- GroupEvent
- ChatGroup
- Chat
- Message
- ChatParticipation
- UserMessageStatus

Confirm each entity's final responsibilities and relationships against the actual Java classes.

## 3.2 Domain Relationships

Proposed conceptual relationships:

- A User can organize multiple Events.
- An Event belongs to a Category.
- A User can request participation in multiple Events.
- An Event can have multiple Participation records.
- A GroupEvent connects an event to its group-related functionality.
- A ChatGroup contains multiple Messages.
- A Message has one sender and belongs to one group.
- ChatParticipation controls membership in a group.

Use explicit JPA mappings and database constraints to enforce the intended relationships.

## 3.3 Aggregates

Suggested aggregate boundaries:

**Event aggregate**

- Event
- Event capacity and lifecycle rules
- Participation decisions, where transactionally appropriate

**ChatGroup aggregate**

- ChatGroup
- Message membership and authorization rules

**User aggregate**

- User profile and authentication identity

Do not assume that these boundaries already exist in the implementation. Refine them as the application grows.

## 3.4 Domain Events

Potential future domain events:

- `UserRegistered`
- `EventCreated`
- `ParticipationRequested`
- `ParticipationAccepted`
- `ParticipationRejected`
- `EventCancelled`
- `MessageSent`

Initially, these can be handled using Spring application events where suitable. For reliable delivery to external systems, consider a transactional outbox and a message broker when justified.

---

# 04. System Architecture

## 4.1 Architecture

Use a layered Spring Boot architecture:

Client → REST Controller → Service → Repository → MySQL

Security filters validate authentication before protected requests reach application logic.

## 4.2 Backend Structure

```text
src/main/java/org/example/travora/
├── Config/
│   ├── SecurityConfig.java
│   └── JwtAuthentificationFilter.java
├── Mapper/
├── controller/
├── dto/
│   ├── request/
│   └── response/
├── model/
├── repository/
├── service/
│   ├── interfaceS/
│   └── implementation classes
└── TravoraApplication.java
```

The structure reflects the current project organization. Standardize package naming and capitalization gradually without introducing unnecessary breaking changes.

## 4.3 Frontend

The frontend technology and implementation status must be documented separately.

Expected responsibilities:

- Authentication and account management.
- Event listing, details, and filters.
- Event creation and editing.
- Participation management.
- Map-based discovery.
- Group chat and notifications.

## 4.4 Infrastructure

Initial development infrastructure:

- Java 17
- Maven
- MySQL 8
- GitHub

Production infrastructure remains to be selected.

## 4.5 Event Architecture

For the first release, use synchronous service calls and database transactions for critical operations.

Introduce asynchronous processing for notifications, email delivery, or other background work when required. Do not introduce Kafka or other infrastructure without a concrete need.

---

# 05. Database Design

## 5.1 Database Tables

Expected tables correspond to the domain entities and their associations, including:

- `users`
- `events`
- `categories`
- `participations`
- `group_events`
- `chat_groups`
- `messages`
- `chat_participations`
- Additional message status tables, if required by the implementation.

Actual table names must be confirmed from Hibernate mappings and the database schema.

## 5.2 Constraints

Recommended constraints:

- Primary key on every table.
- Foreign keys for entity relationships.
- Unique email for users.
- Unique participation per user and event, according to the chosen lifecycle model.
- Non-null constraints for required attributes.
- Valid event dates and geographic coordinates.
- Database-level protection against invalid references.

## 5.3 Indexes

Evaluate indexes for:

- User email.
- Event start date.
- Event category and status.
- Event geographic filtering.
- Participation event ID and user ID.
- Chat group ID and message creation time.

Create indexes based on query patterns and execution plans, rather than adding every possible index.

## 5.4 Migrations

Use Flyway or Liquibase to version schema changes.

Production environments should apply reviewed migrations rather than relying on automatic Hibernate schema updates.

## 5.5 Row-Level Security

MySQL does not provide PostgreSQL-style RLS policies as a standard feature.

Implement authorization in the service layer and enforce database constraints. If PostgreSQL is selected later, evaluate native RLS as an additional security layer.

---

# 06. API Design

## 6.1 API Conventions

- Base path: `/api`
- JSON request and response bodies.
- Use DTOs rather than exposing JPA entities directly.
- Validate input with Jakarta Bean Validation.
- Return consistent HTTP status codes.
- Document endpoints with OpenAPI/Swagger.
- Use pagination for collections.

## 6.2 Proposed REST Endpoints

The routes below are a design proposal; verify them against the existing controllers before implementation.

| Method | Endpoint                          | Purpose                     |
| ------ | --------------------------------- | --------------------------- |
| POST   | `/api/auth/signup`                | Register                    |
| POST   | `/api/auth/signin`                | Authenticate                |
| GET    | `/api/events`                     | List events                 |
| POST   | `/api/events`                     | Create event                |
| GET    | `/api/events/{id}`                | Retrieve event              |
| PUT    | `/api/events/{id}`                | Update event                |
| DELETE | `/api/events/{id}`                | Delete or cancel event      |
| POST   | `/api/events/{id}/participations` | Request participation       |
| GET    | `/api/events/{id}/participations` | List requests               |
| PATCH  | `/api/participations/{id}`        | Decide participation status |
| GET    | `/api/categories`                 | List categories             |
| GET    | `/api/chat-groups/{id}/messages`  | Retrieve group messages     |
| POST   | `/api/chat-groups/{id}/messages`  | Send a message              |

## 6.3 DTO Validation

Examples:

- Email: `@NotBlank` and `@Email`.
- Password: `@NotBlank` with an appropriate minimum length.
- Event description: `@NotBlank` and `@Size`.
- Capacity: `@Min(1)`.
- Coordinates: `@DecimalMin` and `@DecimalMax`, with appropriate bounds.
- Dates: validate the start/end relationship in the service layer.

## 6.4 Error Handling

Adopt a consistent error response:

```json
{
  "timestamp": "2026-10-11T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid event data",
  "path": "/api/events"
}
```

Implement centralized exception handling with `@RestControllerAdvice`.

Never return stack traces, database credentials, JWT secrets, or internal exception details to clients.

---

# 07. UI/UX Design

## 7.1 Main Navigation

Suggested navigation:

- Home
- Explore
- Map
- Create Event
- My Events
- Messages
- Profile

Administrative screens should be accessible only to authorized administrators.

## 7.2 Design System

Define shared design tokens for:

- Primary and secondary colors.
- Typography.
- Spacing.
- Buttons and form controls.
- Cards and dialogs.
- Loading, empty, error, and success states.

## 7.3 Core Screens

1. Landing page.
2. Sign-up and sign-in.
3. Event discovery.
4. Event details.
5. Event creation form.
6. Participation management.
7. Interactive map.
8. Group chat.
9. User profile.
10. Administration and moderation.

## 7.4 User Flows

Design and validate these flows before implementation:

- Register → Sign in → Explore.
- Create Event → Publish → Manage Requests.
- Explore → Event Details → Request Participation.
- Request Accepted → Open Authorized Group Chat.
- Report Content → Administrative Review.

---

# 08. Development Standards

## 8.1 Coding Guidelines

- Keep controllers thin.
- Place business logic in services.
- Use repositories for persistence.
- Use DTOs for external API contracts.
- Validate all client-controlled input.
- Enforce authorization on every sensitive operation.
- Use transactions for related database updates.
- Avoid logging credentials, passwords, and tokens.

## 8.2 Naming Conventions

| Element    | Convention            | Example           |
| ---------- | --------------------- | ----------------- |
| Class      | PascalCase            | `EventService`    |
| Method     | camelCase             | `createEvent()`   |
| Constant   | UPPER_SNAKE_CASE      | `MAX_CAPACITY`    |
| DTO        | Descriptive suffix    | `EventRequest`    |
| Repository | Entity + Repository   | `EventRepository` |
| Endpoint   | Plural resource names | `/api/events`     |

## 8.3 Git Workflow

- `main`: stable, release-ready code.
- `feature/<name>`: new features.
- `fix/<name>`: bug fixes.
- `docs/<name>`: documentation changes.

Example:

```bash
git checkout -b feature/event-search
git add .
git commit -m "Add event search"
git push -u origin feature/event-search
```

Open a pull request and review changes before merging into `main`.

## 8.4 Secrets Management

Never commit real passwords, JWT secrets, SMTP credentials, or production tokens.

Use environment variables or a secret manager. Provide an `application-example.properties` file containing placeholders instead of real values.

---

# 09. Testing Strategy

## 9.1 Unit Tests

Test:

- Event creation rules.
- Participation capacity rules.
- Duplicate participation handling.
- Ownership and permission decisions.
- DTO validation.
- Authentication and token validation.

Tools: JUnit 5, Mockito, and AssertJ where appropriate.

## 9.2 Integration Tests

Test:

- Repository mappings.
- Database constraints.
- Authentication endpoints.
- Event and participation workflows.
- Authorization for private resources.
- Transaction rollback behavior.

Use an isolated test database or Testcontainers.

## 9.3 End-to-End Tests

Validate complete workflows:

1. Register and authenticate.
2. Create an event.
3. Submit a participation request.
4. Approve the request as organizer.
5. Access the authorized group.
6. Send and retrieve a message.
7. Confirm unauthorized access is rejected.

## 9.4 Release Gates

A release should require:

- Successful compilation.
- Passing automated tests.
- No known critical security issues.
- Valid database migrations.
- Working authentication and authorization.
- No committed secrets.
- A documented rollback procedure.

---

# 10. Deployment

## 10.1 CI/CD

Recommended pipeline:

1. Trigger on pull requests and pushes.
2. Set up Java 17.
3. Build with Maven.
4. Run unit and integration tests.
5. Perform dependency and secret scanning.
6. Build a Docker image.
7. Deploy to staging.
8. Run smoke tests.
9. Promote to production after approval.

## 10.2 Environment Variables

Example configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
```

Adapt the property names to the actual configuration classes and JWT implementation.

Production secrets must be generated securely and supplied by the deployment environment.

## 10.3 Hosting

Choose a hosting provider based on budget, regional latency, database support, and operational needs.

Minimum production components:

- Backend application hosting.
- Managed or securely operated MySQL.
- HTTPS.
- Secret management.
- Automated backups.
- Application logs and health monitoring.

## 10.4 Monitoring

Track:

- Application availability.
- HTTP error rates.
- Response times.
- Database connectivity and query performance.
- JVM memory and CPU.
- Authentication failures.
- Deployment failures.

Configure alerts for service outages and failed backups.

---

# 11. Roadmap

## Phase 1 — Foundation

- [x] Initialize Spring Boot project.
- [x] Create initial GitHub repository.
- [x] Implement initial domain models and backend layers.
- [ ] Review entity relationships and schema.
- [ ] Finalize API contracts.
- [ ] Protect all secrets.

## Phase 2 — Core Features

- [ ] Complete registration and authentication.
- [ ] Complete event CRUD and ownership checks.
- [ ] Implement category management.
- [ ] Complete participation workflows.
- [ ] Enforce group chat membership and permissions.
- [ ] Add event filtering and pagination.

## Phase 3 — User Experience

- [ ] Implement frontend.
- [ ] Build event discovery and detail pages.
- [ ] Integrate map and geographic search.
- [ ] Build participation management.
- [ ] Integrate real-time messaging if required.
- [ ] Add responsive design and accessibility.

## Phase 4 — Quality and Security

- [ ] Unit and integration tests.
- [ ] End-to-end critical workflows.
- [ ] Authorization review.
- [ ] Database migration strategy.
- [ ] Rate limiting and abuse protection.
- [ ] Logging and monitoring.

## Phase 5 — Production Release

- [ ] Dockerize the application.
- [ ] Configure CI/CD.
- [ ] Provision production database.
- [ ] Configure HTTPS and secrets.
- [ ] Test backup restoration.
- [ ] Deploy to staging.
- [ ] Complete production smoke tests.
- [ ] Release with a rollback plan.

---

# Definition of Done

A feature is considered complete when:

- Its requirements and acceptance criteria are documented.
- Its implementation follows project conventions.
- Input validation and authorization are enforced.
- Relevant automated tests pass.
- API documentation is updated.
- No secrets are committed.
- Changes are reviewed and merged.
- Deployment and operational impacts are documented.

**Final principle:** document the actual state of the code honestly. Mark features as implemented only after verifying their behavior, and mark proposed architecture and infrastructure as planned until they exist and have been tested.
