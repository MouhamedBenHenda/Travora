# TRAVORA — Product & Services Documentation

**Version:** 1.0  
**Project status:** Initial development  
**Product category:** Travel, local activities and social discovery platform  
**Backend:** Java 17 — Spring Boot 3.5.6  
**Database:** MySQL 8  
**Authentication:** Spring Security + JWT  
**Repository:** https://github.com/MouhamedBenHenda/Travora

---

# 1. Product Overview

## 1.1 What is Travora?

Travora is a social travel and activity discovery platform designed to help people discover places, organize activities, create events and meet other people with similar interests.

The platform allows users to explore activities, view event locations on an interactive map, request participation and communicate with other participants.

Travora aims to make travel and local experiences more accessible by connecting people through shared activities.

## 1.2 Product Vision

To create a platform where users can discover new places, organize meaningful experiences and connect with people who share their interests.

## 1.3 Main Objectives

- Discover local activities and travel experiences.
- Create and manage events.
- Find events based on location, date and category.
- Request participation in events.
- Manage participant requests.
- Communicate through event-related group chats.
- Encourage social interaction through shared experiences.
- Provide a foundation for future travel-planning and recommendation services.

---

# 2. Target Users

## 2.1 Travelers

People who want to explore destinations, discover activities and meet other travelers.

**Example:** A traveler visiting Sidi Bou Said can discover a walking tour or a photography activity organized by another user.

## 2.2 Students and Young Adults

People looking for activities such as hiking, cultural visits, sports, outings and group adventures.

**Example:** A student creates a weekend hiking event and invites other users to participate.

## 2.3 Event Organizers

Users who create activities and manage participation requests.

**Example:** An organizer creates a cultural tour, defines the date and maximum number of participants, then approves requests.

## 2.4 Local Businesses — Future Extension

Tour operators, guides, hostels, restaurants and activity providers could eventually promote their experiences through Travora.

This service is a proposed future extension and is not considered implemented unless the corresponding features are developed.

---

# 3. Core Services

## 3.1 User Authentication and Profiles

Users can create an account and access personalized platform features.

### Main functionalities

- User registration.
- User login.
- JWT-based authentication.
- Secure password storage.
- User profile management.
- Access control for protected operations.

### Expected result

Each authenticated user can manage their own profile and access only the resources authorized for them.

## 3.2 Explore Activities and Events

Travora provides a central place to discover activities created by users.

### Main functionalities

- Display available events.
- View event descriptions and images, when supported.
- View event dates and locations.
- Filter events by category.
- Search by destination or location.
- Display event details.
- View participant capacity and availability.

### Example

A user searches for outdoor activities in Tunisia and discovers a hiking event organized for the weekend.

## 3.3 Event Creation and Management

Authenticated users can organize their own activities.

### Event information

- Event title.
- Description.
- Category.
- Start and end dates.
- Location name.
- Latitude and longitude.
- Maximum number of participants.
- Organizer.
- Event status.

### Organizer services

- Create an event.
- Update event details.
- Cancel an event.
- View participation requests.
- Accept or reject requests.
- Monitor participant capacity.

### Business rules

- Only authorized users can create events.
- Only the organizer or an authorized administrator can manage an event.
- Event dates must be valid.
- An event cannot accept more participants than its capacity.
- Cancelled events cannot accept new participation requests.

## 3.4 Interactive Map and Geographic Discovery

Travora can use a map to help users discover activities based on their geographical location.

### Main functionalities

- Display event markers on a map.
- Associate each event with latitude and longitude.
- Display event information when a marker is selected.
- Open the corresponding event details.
- Filter events by geographic area.
- Support location-based discovery.

### Example user journey

1. The user opens the Explore Map page.
2. The map displays events in the selected area.
3. The user selects an event marker.
4. Travora displays the event title, date, location and available information.
5. The user opens the event details.
6. The user submits a participation request.

### Technical considerations

The backend stores geographical coordinates. A mapping provider such as Google Maps or OpenStreetMap can be integrated into the frontend.

Automatic address lookup and coordinate generation require a geocoding service; they do not happen automatically just because latitude and longitude fields exist.

## 3.5 Participation Management

Travora allows users to request participation in activities created by other users.

### Participant functionalities

- View event details.
- Submit a participation request.
- Check the request status.
- Access event-related communication when authorized.

### Organizer functionalities

- View participation requests.
- Accept a request.
- Reject a request.
- Manage the participant list.

### Suggested participation statuses

- `PENDING`
- `ACCEPTED`
- `REJECTED`
- `CANCELLED`

These statuses must be aligned with the actual Java enum and database implementation.

### Example workflow

User A creates a hiking event. User B requests participation. The organizer reviews the request and accepts it. User B can then access the features reserved for accepted participants.

## 3.6 Group Chat and Communication

Travora can provide group communication associated with activities and events.

### Main functionalities

- Create or access an event-related chat group.
- Display group messages.
- Send messages.
- Associate each message with its sender.
- Store message history.
- Manage group membership.

### Access rules

- Only authorized users can access private groups.
- Only group members can send messages.
- Organizers may have additional management permissions.
- Users must not access conversations belonging to unrelated groups.

Real-time messaging can be implemented with WebSocket and STOMP if it is part of the selected architecture.

## 3.7 Categories and Activity Discovery

Categories help users find activities that match their interests.

### Example categories

- Hiking and outdoor activities.
- Cultural visits.
- Food and local experiences.
- Sports.
- Photography.
- Nightlife.
- Road trips.
- Adventure activities.

These categories are proposed examples and should be adapted to the actual category data.

### Main functionalities

- Display available categories.
- Associate events with categories.
- Filter event listings by category.
- Allow authorized administrators to manage categories.

---

# 4. Proposed Future Services

The following services can extend Travora beyond its initial event-management features. They are proposals, not claims about the current implementation.

## 4.1 Future Trip Planning

Users could add upcoming trips, including destinations and travel dates.

Travora could then identify activities and other users visiting the same destination during overlapping periods.

**Example:** A user planning to visit Paris in December discovers an existing group planning a city tour.

## 4.2 Personalized Recommendations

Travora could recommend activities based on:

- Preferred categories.
- Selected destinations.
- Upcoming travel dates.
- Previously viewed or joined activities.
- Available event capacity.

An AI-based recommendation service could be considered later, after collecting sufficient relevant data and defining privacy safeguards.

## 4.3 Notifications

Users could receive notifications when:

- A participation request is accepted or rejected.
- An organizer updates an event.
- An event is cancelled.
- A new message arrives in an authorized group.
- An upcoming event is approaching.

Email, push notifications and in-app notifications can be introduced incrementally.

## 4.4 Reviews and Community Trust

A future reputation system could help users evaluate shared experiences.

Possible features include:

- Post-event feedback.
- Reporting inappropriate content.
- Blocking abusive accounts.
- Organizer profiles.
- Community moderation.

Reviews and reputation scores should be designed to limit spam, harassment and fraudulent feedback.

## 4.5 Business and Tourism Partnerships

Travora could allow verified local businesses to publish promoted activities or tourism experiences.

Potential services include:

- Business profiles.
- Promoted activity listings.
- External booking links.
- Campaign management.
- Activity engagement analytics.
- Partner dashboards.

Any paid promotion service would require its own business model, payment integration, moderation process and transparent advertising labels.

## 4.6 Live Location Sharing

Users could optionally share their location during an activity to help participants find a meeting point.

This feature would require explicit consent, limited sharing duration, appropriate access controls and clear privacy settings.

---

# 5. Main User Journeys

## 5.1 Discover and Join an Event

1. The user signs in.
2. The user opens the event discovery page.
3. The user filters events by category or location.
4. The user selects an event.
5. The user reviews its details.
6. The user requests participation.
7. The organizer reviews the request.
8. The user receives the decision.
9. If accepted, the user accesses the authorized event communication features.

## 5.2 Create and Manage an Event

1. The organizer signs in.
2. The organizer opens the event creation form.
3. The organizer enters the event information.
4. The organizer selects a category.
5. The organizer defines the date, location and capacity.
6. The system validates the submitted data.
7. The event is created.
8. The organizer manages participation requests.

## 5.3 Communicate with Participants

1. A user becomes an authorized member of an event group.
2. The user opens the associated chat.
3. The user retrieves previous messages.
4. The user sends a message.
5. The system validates group membership and persists the message.
6. Other authorized members can retrieve the message.

---

# 6. Business Rules and Safety

Travora should establish clear rules to protect users and ensure reliable event management.

- Users must be authenticated before performing protected actions.
- Users can modify only resources they own or are authorized to manage.
- Participation requests must respect event status and capacity.
- Duplicate active participation requests must be prevented.
- Private group conversations must remain restricted to authorized members.
- Invalid dates and geographical coordinates must be rejected.
- Users should be able to report inappropriate events or messages when moderation is implemented.
- Sensitive information, passwords and JWT secrets must never be exposed in API responses or logs.

Safety and moderation features should be treated as part of the product design, not merely as optional interface elements.

---

# 7. Product Architecture

## 7.1 Backend

The current backend technology stack includes:

- Java 17.
- Spring Boot 3.5.6.
- Spring Security.
- JWT authentication.
- Spring Data JPA and Hibernate.
- MySQL 8.
- Maven.

The existing repository includes domain concepts for users, events, categories, participation and group messaging. Their exact implementation and completeness should be verified against the source code.

## 7.2 Main Backend Modules

| Module | Responsibility |
|---|---|
| Authentication | Registration, login and token validation |
| Users | Profile and user-related operations |
| Events | Event creation, updates and discovery |
| Categories | Activity classification |
| Participation | Requests and participant management |
| Chat | Group membership and messages |
| Location | Geographic event data and future map queries |
| Administration | Future moderation and platform management |

## 7.3 Suggested Architecture

Client application → REST API → Controller → Service → Repository → MySQL.

A mapping provider, notification provider or recommendation service can be integrated through separate modules when required.

## 7.4 API Design

The API should use:

- REST endpoints.
- JSON request and response bodies.
- DTOs and server-side validation.
- Consistent HTTP status codes.
- Centralized exception handling.
- Pagination for event listings.
- Authentication and authorization for protected resources.

Actual routes must be documented from the implemented controllers rather than assumed from this product specification.

---

# 8. Product Development Roadmap

## Phase 1 — Core Platform

- Complete authentication.
- Complete event management.
- Implement categories.
- Implement participation requests.
- Validate ownership and authorization.
- Complete basic group messaging.

## Phase 2 — Discovery Experience

- Event search and filters.
- Map integration.
- Geographic discovery.
- Event capacity and availability display.
- Improved event details and user profiles.

## Phase 3 — Social Experience

- Real-time chat, if required.
- In-app notifications.
- Event updates and cancellation notifications.
- Reporting and moderation tools.

## Phase 4 — Advanced Travel Services

- Future trip planning.
- Destination-based user discovery.
- Personalized recommendations.
- Reviews and organizer reputation.

## Phase 5 — Business Services

- Verified business accounts.
- Promoted activities.
- Partner dashboards.
- Campaign analytics.
- Booking links and optional monetization.

---

# 9. Product Success Metrics

Travora can measure product adoption and engagement through metrics such as:

- Number of registered users.
- Number of published events.
- Number of participation requests.
- Participation acceptance rate.
- Percentage of events reaching their participant capacity.
- Number of active users.
- Number of messages sent in authorized groups.
- Event cancellation rate.
- User satisfaction after activities.

These metrics should be defined carefully and collected with appropriate privacy safeguards.

---

# 10. Product Positioning

Travora is intended to combine three principal experiences:

1. **Discover:** find events, destinations and activities.
2. **Participate:** request to join activities and coordinate with organizers.
3. **Connect:** communicate with other participants through shared experiences.

The long-term objective is to evolve from a basic event-management platform into a broader travel and social discovery service.

The product should prioritize a reliable event and participation workflow before introducing advanced services such as AI recommendations, business advertising or live location sharing.

---

# 11. Reference Product

Nomadtable is a reference for researching social activity discovery and travel-related community services.

Official references:

- Website: https://www.nomadtable.app/
- App Store listing: https://apps.apple.com/us/app/nomadtable-travel-friends/id6736921913
- Partner services: https://nomadtable.app/partnerships

These references are for product research and inspiration. Travora should define its own user experience, business rules and implementation rather than assume that it already provides the same services.

---

# Final Principle

Travora's documentation distinguishes between existing implementation, planned features and future business opportunities.

A service should be marked as implemented only after its backend behavior, security rules and user workflow have been verified.
