# Travora API

This document describes the HTTP endpoints currently implemented by the Spring controllers in `src/main/java/org/example/travora/controller`. It does not include planned product features or service methods that do not have controller routes.

## API conventions

- Base URL for local development: `http://localhost:8084`
- Request and response format: JSON (`Content-Type: application/json` for requests with a body).
- There are no query-parameter endpoints or controller-level API error schemas.
- No request DTO uses Bean Validation constraints, and controllers do not apply `@Valid`. Consequently, the source does not formally declare required DTO properties. Values described below as necessary are those used by the service logic, not DTO validation guarantees.
- Spring Security currently configures all requests as permitted. A JWT filter reads an optional `Authorization: Bearer <token>` header, but routes are not protected by authorization rules.
- Errors thrown by services (for example, when an ID is not found) have no application-wide exception handler in the source. Their HTTP status and response body are therefore not a stable, explicitly defined API contract.

## Endpoint inventory

| Method | Path | Request | Success response |
|---|---|---|---|
| `POST` | `/api/v1/auth/signup` | `SignUpRequest` JSON | `200 OK`, `JwtAuthenticationResponse` |
| `POST` | `/api/v1/auth/signin` | `SingninRequest` JSON | `200 OK`, `JwtAuthenticationResponse` |
| `POST` | `/api/categories` | `CategoryRequest` JSON | `201 Created`, `CategoryResponse` |
| `GET` | `/api/categories` | None | `200 OK`, `CategoryResponse[]` |
| `GET` | `/api/categories/{categoryId}` | Path parameter `categoryId` | `200 OK`, `CategoryResponse` |
| `PUT` | `/api/categories/{categoryId}` | Path parameter `categoryId`; `CategoryRequest` JSON | `200 OK`, `CategoryResponse` |
| `DELETE` | `/api/categories/{categoryId}` | Path parameter `categoryId` | `204 No Content`, empty body |
| `POST` | `/api/events/add/{userId}` | Path parameter `userId`; `EventRequest` JSON | `201 Created`, `EventResponse` |
| `GET` | `/api/events` | None | `200 OK`, `EventResponse[]` |
| `GET` | `/api/events/{eventId}` | Path parameter `eventId` | `200 OK`, `EventResponse` |
| `PUT` | `/api/events/{eventId}` | Path parameter `eventId`; `EventRequest` JSON | `200 OK`, `EventResponse` |
| `DELETE` | `/api/events/{eventId}` | Path parameter `eventId` | `204 No Content`, empty body |
| `POST` | `/api/v1/chat/{createrId}` | Path parameter `createrId`; `ChatGroupRequest` JSON | `201 Created`, `ChatGroup` entity JSON |
| `GET` | `/api/v1/chat` | None | `200 OK`, `ChatGroupResponse[]` |
| `GET` | `/api/v1/chat/{groupId}` | Path parameter `groupId` | `200 OK`, `ChatGroupResponse` |
| `PUT` | `/api/v1/chat/{groupId}` | Path parameter `groupId`; `ChatGroupResponse` JSON | `200 OK`, `ChatGroup` entity JSON (currently null from service) |
| `DELETE` | `/api/v1/chat/{groupId}` | Path parameter `groupId` | `204 No Content`, empty body |
| `POST` | `/api/v1/chat/{groupId}/members/{memberId}` | Path parameters `groupId`, `memberId` | `200 OK`, `ChatGroup` entity JSON |
| `DELETE` | `/api/v1/chat/{groupId}/members/{memberId}` | Path parameters `groupId`, `memberId` | `200 OK`, `ChatGroup` entity JSON |
| `GET` | `/api/v1/chat/{groupId}/messages` | Path parameter `groupId` | `200 OK`, `Message[]` |
| `POST` | `/api/v2/message/{groupId}/messages` | Path parameter `groupId`; `MessageRequest` JSON | `201 Created`, `Message` entity JSON |
| `GET` | `/api/users` | None | `200 OK`, `User[]` entity JSON |
| `GET` | `/api/users/{userId}` | Path parameter `userId` | `200 OK`, `User` entity JSON |
| `PUT` | `/api/users/{userId}` | Path parameter `userId`; `SignUpRequest` JSON | `200 OK`, `User` entity JSON |
| `POST` | `/api/v1/participations/request/{userId}/{eventId}` | Path parameters `userId`, `eventId`; no body | `200 OK`, `Participation` entity JSON |
| `PUT` | `/api/v1/participations/accept/{participationId}/{creatorId}` | Path parameters `participationId`, `creatorId`; no body | `200 OK`, `Participation` entity JSON |
| `PUT` | `/api/v1/participations/reject/{participationId}/{creatorId}` | Path parameters `participationId`, `creatorId`; no body | `200 OK`, `Participation` entity JSON |

Authentication, chat, and participation use `/api/v1`; messages use `/api/v2`; categories, events, and users are unversioned.

## Request body types

All properties below have Java types; JSON names are lower camel case as exposed by the DTO accessors unless called out in the notes. No request DTO has explicit nullability or validation annotations.

### `SignUpRequest`

Used by `POST /api/v1/auth/signup`.

| Property | JSON type | Notes |
|---|---|---|
| `firstName` | string | |
| `lastName` | string | |
| `email` | string | Used as the account email. |
| `password` | string | Sent in the request body; do not log or expose it. |
| `role` | string or `null` | `ADMIN`, `CREATOR`, `NORMAL_ADVENTURER`, or `USER`. If omitted or `null`, the service selects `NORMAL_ADVENTURER`. |

Example:

```json
{
  "firstName": "Amina",
  "lastName": "Ben Ali",
  "email": "amina@example.com",
  "password": "example-password",
  "role": "NORMAL_ADVENTURER"
}
```

### `SingninRequest`

Used by `POST /api/v1/auth/signin` (the request class is spelled `SingninRequest` in the source).

| Property | JSON type |
|---|---|
| `email` | string |
| `password` | string |

Example:

```json
{
  "email": "amina@example.com",
  "password": "example-password"
}
```

### `CategoryRequest`

Used by `POST /api/categories` and `PUT /api/categories/{categoryId}`.

| Property | JSON type |
|---|---|
| `name` | string |

Example:

```json
{
  "name": "Hiking"
}
```

### `EventRequest`

Used by `POST /api/events/add/{userId}` and `PUT /api/events/{eventId}`.

| Property | JSON type | Notes |
|---|---|---|
| `nameEvent` | string | Used by the update service; not copied into a new event by the current create service. |
| `description` | string | |
| `date_Begin_Event` | string (date-time) | Java `Date`; Jackson is configured not to serialize dates as numeric timestamps. |
| `date_final_Event` | string (date-time) | Java `Date`; Jackson is configured not to serialize dates as numeric timestamps. |
| `placeEvent` | string | |
| `latitude` | number | Java `Double`. |
| `categoryId` | string | Backing Java field is `CategoryId`; used to look up the category. |
| `venue` | string | Present in the request DTO but not copied by the current event service. |
| `longitude` | number | Java `Double`. |
| `nbr_Of_Personne` | integer | Java `Long`. |
| `startDateSale` | string (date-time) | Java `LocalDateTime`. |
| `finalDateofSale` | string (date-time) | Java `LocalDateTime`. |
| `eventType` | string | `FREE` or `PRICE`. |
| `ticketTypeRequests` | array | Each `CreateTicketTypeRequest` has `name`, `price`, and `totalAvailable`; currently not processed by `EventServiceImpl`. |

The event creator ID is supplied as the `{userId}` path parameter, not in this body.

Example:

```json
{
  "nameEvent": "Coastal walking tour",
  "description": "Coastal walking tour",
  "date_Begin_Event": "2026-11-15T09:00:00Z",
  "date_final_Event": "2026-11-15T13:00:00Z",
  "placeEvent": "Sidi Bou Said",
  "latitude": 36.8700,
  "categoryId": "category-id",
  "longitude": 10.3470,
  "nbr_Of_Personne": 12,
  "startDateSale": "2026-11-10T08:00:00",
  "finalDateofSale": "2026-11-12T18:00:00",
  "eventType": "PRICE",
  "venue": "Harbor entrance",
  "ticketTypeRequests": [
    {
      "name": "Standard",
      "price": 25.0,
      "totalAvailable": 12
    }
  ]
}
```

### `ChatGroupRequest`

Used by `POST /api/v1/chat/{createrId}`.

| Property | JSON type | Notes |
|---|---|---|
| `name` | string | |
| `description` | string | |
| `userCreateChatId` | string | Ignored by this endpoint; the creator is taken from `{createrId}` in the URL. |
| `memberId` | array of strings | |

The creator is supplied by the `{createrId}` path parameter. The service sets the group's creator ID from that path value and does not use `userCreateChatId` from this request body. The service initializes an empty member list when `memberId` is `null`.

Example:

```json
{
  "name": "Coastal Walk",
  "description": "Coordination for the walking tour",
  "memberId": ["user-id", "another-user-id"]
}
```

### `MessageRequest`

Used by `POST /api/v2/message/{groupId}/messages`.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | Present on the request DTO but not used when the service creates the message. |
| `content` | string | Backing Java field is capitalized `Content`; use the JSON property `content`. |
| `senderId` | string | |
| `chatGroupId` | string | Present on the request DTO but not used; the path parameter `{groupId}` selects the group. |
| `messageType` | string | `MEDIA`, `TEXT`, or `AUDIO`. |

Example:

```json
{
  "id": "client-message-id",
  "content": "Meet at the main entrance.",
  "senderId": "user-id",
  "chatGroupId": "ignored-group-id",
  "messageType": "TEXT"
}
```

## Response types

### `JwtAuthenticationResponse`

Returned on successful signup and signin (`200 OK`).

| Property | JSON type | Notes |
|---|---|---|
| `token` | string | JWT generated by the authentication service. |
| `userId` | string | Persisted user ID. |
| `role` | string | Role name. |
| `refreshToken` | string or `null` | Present on the response class but not populated by the current signup/signin implementation. |

The signin controller has a `400 Bad Request` branch only if the service returns a null response or token. In the current service, failed authentication throws before that branch; no stable error response is defined.

### `CategoryResponse`

Returned by category create, read, update, and list endpoints.

| Property | JSON type |
|---|---|
| `id` | string |
| `name` | string |

### `EventResponse`

Returned by event create, read, update, and list endpoints.

| Property | JSON type |
|---|---|
| `event` | string |
| `id` | string |
| `description` | string |
| `date_Begin_Event` | string (date-time) |
| `date_final_Event` | string (date-time) |
| `placeEvent` | string |
| `latitude` | number |
| `longitude` | number |
| `nbr_Of_Personne` | integer |
| `role` | string |
| `userId` | string |
| `categoryId` | string |
| `startDateSale` | string (date-time) |
| `finalDateofSale` | string (date-time) |
| `eventType` | string |

The current mapper does not set `event` (or the event name), so `event` will be null in the mapped response. `venue` and `ticketTypeRequests` are not included in `EventResponse`.

### `Participation`

Returned directly by the participation endpoints. The associated `user`, `event`, `chat`, and `chatGroup` relationships are annotated `@JsonIgnore`; the JSON response therefore exposes the participation's own fields, including `id` and `status`.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | Participation UUID. |
| `status` | string | `PENDING`, `ACCEPTED`, or `REJECTED`. |

### `ChatGroup` entity

Returned directly by chat group creation, update, and member-management operations. List and get-by-ID return `ChatGroupResponse` instead.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | Group UUID. |
| `name` | string | Group name. |
| `description` | string | Group description. |
| `userCreateChatId` | string | Creator user ID. |
| `memberId` | array of strings | Member user IDs. |
| `messages` | array of objects | Chat messages; may be `null` or empty. |

### `Message`

Returned by message creation and group message read endpoints.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | Message UUID. |
| `content` | string | Message text. |
| `senderId` | string | Sender user ID. |
| `messageType` | string | Enum value such as `TEXT`, `MEDIA`, or `AUDIO`. |
| `messageState` | string | State enum. |
| `mediaFilePath` | string or null | Optional media attachment path. |

### `User`

Returned by user read endpoints.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | User UUID. |
| `password` | string | Included in entity payload. |
| `mail` | string | Email address. |
| `firstname` | string | First name. |
| `lastName` | string | Last name. |
| `age` | integer | User age. |
| `role` | string | User role. |
| `userMessageStatus` | string | Message status enum. |
| `lastSeen` | string (date-time) | Last online timestamp. |

### `ChatGroupResponse`

Returned by the chat group list/read endpoints.

| Property | JSON type | Notes |
|---|---|---|
| `id` | string | Group UUID. |
| `name` | string | Group name. |
| `description` | string | Group description. |
| `userCreateChatId` | string | Creator user ID. |
| `memberId` | array of strings | Group member IDs. |
| `messageIds` | array of strings | Message IDs. |
| `createAt` | string (date-time) | Creation timestamp. |

### Unused response DTOs

`EventGroupResponse` and `MessageResponse` exist in the project but are not currently returned by any controller route. They are not part of the current public HTTP contract.

## Additional chat and user endpoints

These routes are also implemented in the current controllers and are listed in the endpoint inventory above:

| Method | Path | Request | Success response |
|---|---|---|---|
| `GET` | `/api/v1/chat` | None | `200 OK`, `List<ChatGroupResponse>` |
| `GET` | `/api/v1/chat/{groupId}` | Path parameter `groupId` | `200 OK`, `ChatGroupResponse` |
| `PUT` | `/api/v1/chat/{groupId}` | Path parameter `groupId`; `ChatGroupResponse` JSON | `200 OK`, `ChatGroup` |
| `DELETE` | `/api/v1/chat/{groupId}` | Path parameter `groupId` | `204 No Content` |
| `POST` | `/api/v1/chat/{groupId}/members/{memberId}` | Path params `groupId`, `memberId` | `200 OK`, `ChatGroup` |
| `DELETE` | `/api/v1/chat/{groupId}/members/{memberId}` | Path params `groupId`, `memberId` | `200 OK`, `ChatGroup` |
| `GET` | `/api/v1/chat/{groupId}/messages` | Path parameter `groupId` | `200 OK`, `List<Message>` |
| `GET` | `/api/users/{userId}` | Path parameter `userId` | `200 OK`, `User` |
| `GET` | `/api/users` | None | `200 OK`, `List<User>` |
| `PUT` | `/api/users/{userId}` | Path parameter `userId`; `SignUpRequest` JSON | `200 OK`, `User` |

## Notes

- `SecurityConfig` uses `anyRequest().permitAll()`, so the app currently does not enforce role-based access at the API boundary.
- `@Valid` is not used in the controllers, so validation rules are not formally expressed in the API contract.
- The source does not define an application-wide `@ControllerAdvice` or global error response schema.
- `GET /api/users` and `GET /api/users/{userId}` return the `User` entity directly, which includes the `password` property. Do not expose these responses to untrusted clients; the current API does not redact this field.
- `PUT /api/v1/chat/{groupId}` is routed but the service implementation currently returns `null`, so it responds `200 OK` with a null response body rather than an updated group.
- `EventRequest` now includes `venue` and `ticketTypeRequests`, but the current `EventServiceImpl` does not copy/process those values. The create implementation also does not copy `nameEvent`; the update implementation does.
- The project has DTOs and model classes that are not surfaced by active controller routes; they are not part of the public API unless a controller exposes them.

### ChatGroup entity serialization

Returned directly by chat creation, update, and member-management routes (not as `ChatGroupResponse`). The serialized entity includes its group data, member IDs, message collection, and inherited audit timestamps (`creLocalDate` and `lastDate`). The associated user relationship is ignored for JSON serialization. The update service currently returns `null`.

### `Message` entity

Returned directly by `POST /api/v2/message/{groupId}/messages`. Its model contains `id`, `content`, `senderId`, `messageState`, `messageType`, `chatParticipation`, `mediaFilePath`, and inherited audit timestamps. Its `chatGroup` and `chat` relationships are marked `@JsonIgnore`. This endpoint does not use the `MessageResponse` DTO.

## Endpoint details

### Authentication

#### `POST /api/v1/auth/signup`

- **Body:** `SignUpRequest`
- **Success:** `200 OK` with `JwtAuthenticationResponse`
- **Notes:** Role defaults to `NORMAL_ADVENTURER` when not supplied. No refresh token is generated by the current service.

#### `POST /api/v1/auth/signin`

- **Body:** `SingninRequest`
- **Success:** `200 OK` with `JwtAuthenticationResponse`
- **Failure:** No application-defined error body/status. Authentication exceptions are not mapped by a controller advice in this project.

### Categories

#### `POST /api/categories`

- **Body:** `CategoryRequest`
- **Success:** `201 Created` with `CategoryResponse`

#### `GET /api/categories`

- **Body:** None
- **Success:** `200 OK` with an array of `CategoryResponse`

#### `GET /api/categories/{categoryId}`

- **Path:** `categoryId` (string)
- **Success:** `200 OK` with `CategoryResponse`
- **Missing category:** The service throws a runtime exception; no stable HTTP error mapping is declared.

#### `PUT /api/categories/{categoryId}`

- **Path:** `categoryId` (string)
- **Body:** `CategoryRequest`
- **Success:** `200 OK` with `CategoryResponse`
- **Missing category:** The service throws a runtime exception; no stable HTTP error mapping is declared.

#### `DELETE /api/categories/{categoryId}`

- **Path:** `categoryId` (string)
- **Success:** `204 No Content` with no response body
- **Missing category:** The service throws a runtime exception; no stable HTTP error mapping is declared.

### Events

#### `POST /api/events/add/{userId}`

- **Path:** `userId` (string)
- **Body:** `EventRequest`
- **Success:** `201 Created` with `EventResponse`
- **Service lookups:** Uses `userId` to find the creator and `categoryId` from the request body to find the category. Lookup failures are not mapped to a documented error response.

#### `GET /api/events`

- **Body:** None
- **Success:** `200 OK` with an array of `EventResponse`

#### `GET /api/events/{eventId}`

- **Path:** `eventId` (string)
- **Success:** `200 OK` with `EventResponse`
- **Missing event:** The service throws a runtime exception; no stable HTTP error mapping is declared.

#### `PUT /api/events/{eventId}`

- **Path:** `eventId` (string)
- **Body:** `EventRequest`
- **Success:** `200 OK` with `EventResponse`
- **Notes:** The update also looks up the request's `categoryId`. Missing event/category errors are not mapped to a documented error response.

#### `DELETE /api/events/{eventId}`

- **Path:** `eventId` (string)
- **Success:** `204 No Content` with no response body
- **Missing event:** The service throws a runtime exception; no stable HTTP error mapping is declared.

### Chat and messages

#### `POST /api/v1/chat/{createrId}`

- **Path:** `createrId` (string; ID of an existing user)
- **Body:** `ChatGroupRequest`
- **Success:** `201 Created` with a serialized `ChatGroup` entity
- **Service behavior:** Looks up the user identified by `createrId`, associates that user with the group, and sets `userCreateChatId` to the path value. The body's `userCreateChatId` property is ignored; `memberId` is copied when supplied and defaults to an empty list when `null`.
- **Missing creator:** The service throws a runtime exception; no stable HTTP error mapping is declared.
- **Notes:** The controller also exposes chat listing, reading, updating, deleting, member management, and message listing routes (see the endpoint inventory). The update service currently returns `null`.

#### `POST /api/v2/message/{groupId}/messages`

- **Path:** `groupId` (string)
- **Body:** `MessageRequest`
- **Success:** `201 Created` with a serialized `Message` entity
- **Notes:** The service uses the path `groupId` to find the group. The request's `chatGroupId` and `id` are not used. A missing group causes a runtime exception with no documented error mapping.

### Users

#### `GET /api/users`

- **Success:** `200 OK` with a list of serialized `User` entities.
- **Important:** The entity includes the `password` field in its JSON representation; this route currently returns it without redaction.

#### `GET /api/users/{userId}`

- **Path:** `userId` (string)
- **Success:** `200 OK` with a serialized `User` entity.
- **Important:** The entity includes the `password` field in its JSON representation; this route currently returns it without redaction.

#### `PUT /api/users/{userId}`

- **Path:** `userId` (string)
- **Body:** `SignUpRequest`
- **Success:** `200 OK` with a serialized `User` entity.

### Participation

#### `POST /api/v1/participations/request/{userId}/{eventId}`

- **Path:** `userId`, `eventId` (strings)
- **Body:** None
- **Success:** `200 OK` with a serialized `Participation` entity; the initial status is `PENDING`.
- **Failure behavior:** Throws a runtime exception if the user or event does not exist, or if a participation for that user/event already exists. There is no stable error mapping.

#### `PUT /api/v1/participations/accept/{participationId}/{creatorId}`

- **Path:** `participationId`, `creatorId` (strings)
- **Body:** None
- **Success:** `200 OK` with a serialized `Participation` entity with status `ACCEPTED`.
- **Failure behavior:** Throws a runtime exception if the participation/event/chat group is missing or the participation is not pending. The current implementation looks up `creatorId` and assigns that user to the participation, rather than checking that the ID belongs to the event creator.

#### `PUT /api/v1/participations/reject/{participationId}/{creatorId}`

- **Path:** `participationId`, `creatorId` (strings)
- **Body:** None
- **Success:** `200 OK` with a serialized `Participation` entity with status `REJECTED`.
- **Failure behavior:** Throws a runtime exception if the participation/event is missing, the path `creatorId` is not the event creator, or the participation is not pending. There is no stable error mapping. On success, the implementation also changes the creator user's role to `USER`.

## Not exposed as HTTP endpoints

Event-group management, ticket-type management, and application management/health APIs have DTOs or model/service code but no routes in the current controller source. Features mentioned only as proposed or planned in `docs/PROJECT_DOCUMENTATION.md` are not included in this API contract.
