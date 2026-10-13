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
| `POST` | `/api/v2/message/{groupId}/messages` | Path parameter `groupId`; `MessageRequest` JSON | `201 Created`, `Message` entity JSON |

The `/v1` and `/v2` prefixes appear only on authentication/chat and message routes respectively; the categories and events routes are unversioned.

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
| `description` | string | |
| `date_Begin_Event` | string (date-time) | Java `Date`; Jackson is configured not to serialize dates as numeric timestamps. |
| `date_final_Event` | string (date-time) | Java `Date`; Jackson is configured not to serialize dates as numeric timestamps. |
| `placeEvent` | string | |
| `latitude` | number | Java `Double`. |
| `categoryId` | string | Backing Java field is `CategoryId`; the service uses this value to look up the category. |
| `longitude` | number | Java `Double`. |
| `nbr_Of_Personne` | integer | Java `Long`. |

The event creator ID is supplied as the `{userId}` path parameter, not in this body.

Example:

```json
{
  "description": "Coastal walking tour",
  "date_Begin_Event": "2026-11-15T09:00:00Z",
  "date_final_Event": "2026-11-15T13:00:00Z",
  "placeEvent": "Sidi Bou Said",
  "latitude": 36.8700,
  "categoryId": "category-id",
  "longitude": 10.3470,
  "nbr_Of_Personne": 12
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

### `ChatGroup` entity

Returned directly by `POST /api/v1/chat/{createrId}` (not as `ChatGroupResponse`). The serialized entity includes its group data, member IDs, message collection, and inherited audit timestamps (`creLocalDate` and `lastDate`). The associated user relationship is ignored for JSON serialization. This endpoint does not use a dedicated response DTO.

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
- **Notes:** The API only exposes group creation; service interface methods for listing, reading, updating, deleting, and managing group membership do not have controller routes.

#### `POST /api/v2/message/{groupId}/messages`

- **Path:** `groupId` (string)
- **Body:** `MessageRequest`
- **Success:** `201 Created` with a serialized `Message` entity
- **Notes:** The service uses the path `groupId` to find the group. The request's `chatGroupId` and `id` are not used. A missing group causes a runtime exception with no documented error mapping.

## Not exposed as HTTP endpoints

The source contains participation-related services and domain types but no participation controller or participation HTTP routes. The same applies to user/profile operations, event-group management and application management/health endpoints. APIs or functions listed only as proposed/planned in `docs/PROJECT_DOCUMENTATION.md` are not included here.
