## System Architecture

```mermaid
graph TD
    %% Define Nodes
    User((User))
    Frontend["Frontend Client\n(React)"]
    BackendREST["REST API\n(Spring Boot /RoomController)"]
    BackendWS["WebSocket API\n(Spring Boot /ChatController)"]
    Broker(("In-Memory Broker\n(STOMP Pub/Sub)"))
    Database[("MongoDB\n(Rooms & Messages)")]

    %% User Interaction
    User <-->|Interacts via Browser| Frontend

    %% HTTP / REST Data Flow
    Frontend -->|1. Create/Join Room (HTTP POST/GET)| BackendREST
    Frontend -->|2. Fetch Chat History (HTTP GET)| BackendREST
    BackendREST <-->|Query / Persist Room Data| Database

    %% WebSocket / Real-Time Data Flow
    Frontend <-->|3. Send Message (SockJS/STOMP)| BackendWS
    BackendWS -->|4. Append Message to Room| Database
    BackendWS -->|5. Route Message via Topic| Broker
    Broker -.->|6. Broadcast to Room Subscribers| Frontend
```
