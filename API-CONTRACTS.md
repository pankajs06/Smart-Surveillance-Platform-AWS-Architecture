# Drone Backend API Contracts

**Base URL:** `http://localhost:8080`

**Authentication:** Bearer Token (JWT) - pass in header: `Authorization: Bearer <token>`

---

## 1. Authentication API

### POST `/api/auth/login`
**Description:** User login with credentials

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "string",
  "password": "string"
}
```

**Response (200 OK):**
```json
{
  "accessToken": "string",
  "refreshToken": "string",
  "expiresIn": 3600,
  "user": {
    "id": "string",
    "name": "string",
    "roles": ["string"]
  }
}
```

**Status Codes:**
- `200` - Successful login
- `401` - Invalid credentials

---

### POST `/api/auth/refresh`
**Description:** Refresh access token

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "refreshToken": "string"
}
```

**Response (200 OK):**
```json
{
  "accessToken": "string",
  "refreshToken": "string",
  "expiresIn": 3600
}
```

---

## 2. Camera Management API

### POST `/api/cameras`
**Description:** Register new camera

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "siteId": "site-1",
  "label": "Front Gate Camera",
  "rtspUrl": "rtsp://192.168.1.100:554/stream",
  "status": "online"
}
```

**Response (201 Created):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "siteId": "site-1",
  "label": "Front Gate Camera",
  "rtspUrl": "rtsp://192.168.1.100:554/stream",
  "status": "online",
  "lastSeen": "2026-06-05T19:34:41Z"
}
```

**Status Codes:**
- `201` - Camera registered
- `400` - Invalid request
- `401` - Unauthorized

---

### GET `/api/cameras`
**Description:** List all cameras or filter by site

**Headers:**
```
Authorization: Bearer <token>
```

**Query Parameters:**
- `siteId` (optional) - Filter by site ID

**Response (200 OK):**
```json
[
  {
    "id": "uuid",
    "cameraId": "cam-001",
    "siteId": "site-1",
    "label": "Front Gate Camera",
    "rtspUrl": "rtsp://192.168.1.100:554/stream",
    "status": "online",
    "lastSeen": "2026-06-05T19:34:41Z"
  }
]
```

**Status Codes:**
- `200` - Success
- `401` - Unauthorized

---

### GET `/api/cameras/{id}`
**Description:** Get camera details by ID

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Camera UUID

**Response (200 OK):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "siteId": "site-1",
  "label": "Front Gate Camera",
  "rtspUrl": "rtsp://192.168.1.100:554/stream",
  "status": "online",
  "lastSeen": "2026-06-05T19:34:41Z"
}
```

**Status Codes:**
- `200` - Success
- `404` - Camera not found
- `401` - Unauthorized

---

### PUT `/api/cameras/{id}`
**Description:** Update camera details

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Camera UUID

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "siteId": "site-1",
  "label": "Front Gate Camera",
  "rtspUrl": "rtsp://192.168.1.100:554/stream",
  "status": "online"
}
```

**Response (200 OK):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "siteId": "site-1",
  "label": "Front Gate Camera",
  "rtspUrl": "rtsp://192.168.1.100:554/stream",
  "status": "online",
  "lastSeen": "2026-06-05T19:34:41Z"
}
```

**Status Codes:**
- `200` - Updated
- `404` - Camera not found
- `401` - Unauthorized

---

### DELETE `/api/cameras/{id}`
**Description:** Delete camera

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Camera UUID

**Response (204 No Content)**

**Status Codes:**
- `204` - Deleted
- `404` - Camera not found
- `401` - Unauthorized

---

## 3. Recording Management API

### POST `/api/recordings`
**Description:** Create recording entry

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "startAt": "2026-06-05T10:00:00Z",
  "endAt": "2026-06-05T11:00:00Z",
  "sizeBytes": 5000000,
  "storageUrl": "s3://bucket/recordings/rec-001"
}
```

**Response (201 Created):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "startAt": "2026-06-05T10:00:00Z",
  "endAt": "2026-06-05T11:00:00Z",
  "sizeBytes": 5000000,
  "storageUrl": "s3://bucket/recordings/rec-001"
}
```

**Status Codes:**
- `201` - Recording created
- `400` - Invalid request
- `401` - Unauthorized

---

### GET `/api/recordings`
**Description:** List recordings with optional filtering

**Headers:**
```
Authorization: Bearer <token>
```

**Query Parameters:**
- `cameraId` (optional) - Filter by camera ID
- `start` (optional) - ISO 8601 start date
- `end` (optional) - ISO 8601 end date

**Response (200 OK):**
```json
[
  {
    "id": "uuid",
    "cameraId": "cam-001",
    "startAt": "2026-06-05T10:00:00Z",
    "endAt": "2026-06-05T11:00:00Z",
    "sizeBytes": 5000000,
    "storageUrl": "s3://bucket/recordings/rec-001"
  }
]
```

**Status Codes:**
- `200` - Success
- `401` - Unauthorized

---

### GET `/api/recordings/{id}`
**Description:** Get recording details

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Recording UUID

**Response (200 OK):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "startAt": "2026-06-05T10:00:00Z",
  "endAt": "2026-06-05T11:00:00Z",
  "sizeBytes": 5000000,
  "storageUrl": "s3://bucket/recordings/rec-001"
}
```

**Status Codes:**
- `200` - Success
- `404` - Recording not found
- `401` - Unauthorized

---

### GET `/api/recordings/{id}/metadata`
**Description:** Get recording metadata and segments

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Recording UUID

**Response (200 OK):**
```json
{
  "id": "uuid",
  "cameraId": "cam-001",
  "startAt": "2026-06-05T10:00:00Z",
  "endAt": "2026-06-05T11:00:00Z",
  "sizeBytes": 5000000,
  "storageUrl": "s3://bucket/recordings/rec-001"
}
```

**Status Codes:**
- `200` - Success
- `404` - Recording not found
- `401` - Unauthorized

---

### POST `/api/recordings/{id}/generate-playback-url`
**Description:** Generate signed playback URL

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Recording UUID

**Request Body:**
```json
{
  "protocol": "hls",
  "ttl": 300
}
```

**Response (200 OK):**
```json
{
  "playbackUrl": "https://cdn.example.com/rec-001/playlist.m3u8?sig=abc",
  "expiresAt": "2026-06-05T20:00:00Z"
}
```

**Status Codes:**
- `200` - URL generated
- `404` - Recording not found
- `401` - Unauthorized

---

### PUT `/api/recordings/{id}`
**Description:** Update recording

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Recording UUID

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "startAt": "2026-06-05T10:00:00Z",
  "endAt": "2026-06-05T11:00:00Z",
  "sizeBytes": 5000000,
  "storageUrl": "s3://bucket/recordings/rec-001"
}
```

**Response (200 OK):** Recording object

**Status Codes:**
- `200` - Updated
- `404` - Not found
- `401` - Unauthorized

---

### DELETE `/api/recordings/{id}`
**Description:** Delete recording

**Headers:**
```
Authorization: Bearer <token>
```

**Response (204 No Content)**

**Status Codes:**
- `204` - Deleted
- `404` - Not found
- `401` - Unauthorized

---

### GET `/api/recordings/timelines/{siteId}`
**Description:** Get recording timeline for site

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `siteId` - Site ID

**Query Parameters:**
- `from` (optional) - ISO 8601 start date
- `to` (optional) - ISO 8601 end date

**Response (200 OK):**
```json
{
  "siteId": "site-1",
  "range": {
    "from": "2026-06-05T00:00:00Z",
    "to": "2026-06-05T23:59:59Z"
  },
  "tracks": [
    {
      "cameraId": "cam-001",
      "segments": [
        {
          "start": "2026-06-05T10:00:00Z",
          "end": "2026-06-05T11:00:00Z",
          "thumbnail": "https://cdn.example.com/thumb-1.jpg",
          "recordingId": "uuid"
        }
      ]
    }
  ]
}
```

**Status Codes:**
- `200` - Success
- `401` - Unauthorized

---

## 4. On-Prem Agent Management

### POST `/api/onprem/agents`
**Description:** Register on-prem agent

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "agentId": "agent-1",
  "siteId": "site-1",
  "ip": "192.168.1.50",
  "capabilities": ["transcode", "edge-record", "webrtc-proxy"]
}
```

**Response (201 Created):**
```json
{
  "id": "uuid",
  "agentId": "agent-1"
}
```

**Status Codes:**
- `201` - Agent registered
- `400` - Invalid request

---

### POST `/api/onprem/agents/{id}/heartbeat`
**Description:** Agent health heartbeat

**Headers:**
```
Content-Type: application/json
```

**Path Parameters:**
- `id` - Agent UUID

**Request Body:**
```json
{
  "status": "healthy",
  "cpu": 45.5,
  "mem": 62.3,
  "streamsActive": 5,
  "timestamp": "2026-06-05T19:34:41Z"
}
```

**Response (200 OK):**
```json
{
  "ok": true
}
```

**Status Codes:**
- `200` - Heartbeat received
- `404` - Agent not found

---

### GET `/api/onprem/agents/{id}/capabilities`
**Description:** Get agent capabilities

**Path Parameters:**
- `id` - Agent UUID

**Response (200 OK):**
```json
{
  "agentId": "agent-1",
  "capabilities": ["transcode", "edge-record", "webrtc-proxy"]
}
```

**Status Codes:**
- `200` - Success
- `404` - Agent not found

---

## 5. Stream Ingest API

### POST `/api/ingest/announce`
**Description:** Announce stream availability from on-prem agent

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "agentId": "agent-1",
  "cameraId": "cam-001",
  "streams": [
    {
      "protocol": "rtsp",
      "url": "rtsp://agent:554/stream",
      "codec": "h264",
      "resolution": "1920x1080"
    }
  ]
}
```

**Response (200 OK):**
```json
{
  "accepted": true,
  "ingestId": "ingest-123"
}
```

**Status Codes:**
- `200` - Stream announced
- `400` - Invalid request

---

### POST `/api/ingest/signed-url`
**Description:** Generate signed ingest URL

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "agentId": "agent-1",
  "cameraId": "cam-001",
  "protocol": "rtmp",
  "ttlSeconds": 3600
}
```

**Response (200 OK):**
```json
{
  "url": "rtmp://ingest.example.com/live/abc123",
  "method": "POST",
  "expiresAt": "2026-06-05T20:34:41Z"
}
```

**Status Codes:**
- `200` - URL generated
- `400` - Invalid request

---

## 6. Stream Playback API

### GET `/api/streams/available`
**Description:** List available streams for a camera

**Headers:**
```
Authorization: Bearer <token>
```

**Query Parameters:**
- `cameraId` (required) - Camera ID
- `profile` (optional) - e.g., "720p", "360p"

**Response (200 OK):**
```json
[
  {
    "protocol": "webrtc",
    "profile": "720p",
    "latencyMs": 500
  },
  {
    "protocol": "hls",
    "profile": "720p",
    "latencyMs": 3000
  }
]
```

**Status Codes:**
- `200` - Success
- `401` - Unauthorized

---

### POST `/api/streams/watch`
**Description:** Start watching live stream

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "profile": "720p",
  "protocol": "webrtc",
  "preferLowLatency": true
}
```

**Response (200 OK) - WebRTC:**
```json
{
  "sessionId": "session-123",
  "webrtcOffer": {
    "sdp": "v=0\r\n..."
  },
  "signalingUrl": "wss://signaling.example.com/session-123"
}
```

**Response (200 OK) - HLS:**
```json
{
  "playbackUrl": "https://cdn.example.com/cam-001/playlist.m3u8?sig=abc"
}
```

**Status Codes:**
- `200` - Stream started
- `400` - Invalid request
- `401` - Unauthorized

---

### POST `/api/streams/stop`
**Description:** Stop watching stream

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "sessionId": "session-123"
}
```

**Response (200 OK):**
```json
{
  "stopped": true
}
```

**Status Codes:**
- `200` - Success
- `401` - Unauthorized

---

### POST `/api/streams/subscribe`
**Description:** Subscribe to multiple camera streams

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "sessionId": "session-123",
  "cameraIds": ["cam-001", "cam-002"],
  "profiles": ["720p", "360p"],
  "layout": "grid"
}
```

**Response (200 OK):**
```json
{
  "subscriptionId": "sub-123",
  "streams": [
    {
      "cameraId": "cam-001",
      "playbackUrl": "https://cdn.example.com/cam-001/playlist.m3u8"
    },
    {
      "cameraId": "cam-002",
      "playbackUrl": "https://cdn.example.com/cam-002/playlist.m3u8"
    }
  ],
  "composite": {
    "url": "https://cdn.example.com/composite/playlist.m3u8"
  }
}
```

**Status Codes:**
- `200` - Subscription created
- `401` - Unauthorized

---

## 7. Video Comparison API

### POST `/api/compare`
**Description:** Create video comparison session

**Headers:**
```
Content-Type: application/json
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "leftRecordingId": "rec-001",
  "rightRecordingId": "rec-002",
  "leftStart": "2026-06-05T10:00:00Z",
  "rightStart": "2026-06-05T10:00:00Z"
}
```

**Response (202 Accepted):**
```json
{
  "compareSessionId": "cmp-123",
  "status": "queued"
}
```

**Status Codes:**
- `202` - Session queued
- `400` - Invalid request
- `401` - Unauthorized

---

### GET `/api/compare/{id}/status`
**Description:** Get comparison session status

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id` - Comparison session ID

**Response (200 OK):**
```json
{
  "compareSessionId": "cmp-123",
  "status": "ready",
  "left": {
    "playbackUrl": "https://cdn.example.com/left/playlist.m3u8"
  },
  "right": {
    "playbackUrl": "https://cdn.example.com/right/playlist.m3u8"
  }
}
```

**Status Codes:**
- `200` - Success
- `404` - Session not found
- `401` - Unauthorized

---

## 8. Internal APIs (Backend-Only)

### POST `/api/internal/streams/allocate`
**Description:** Allocate stream resources (internal use)

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "cameraId": "cam-001",
  "agentId": "agent-1",
  "targetProfiles": ["720p", "360p"],
  "protocol": "webrtc",
  "sessionId": "session-123"
}
```

**Response (201 Created):**
```json
{
  "allocationId": "alloc-123",
  "proxyEndpoint": "rtmp://proxy:1935/stream",
  "expiresAt": "2026-06-05T20:34:41Z"
}
```

**Status Codes:**
- `201` - Allocated
- `400` - Invalid request

---

### POST `/api/internal/streams/release`
**Description:** Release stream allocation

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "allocationId": "alloc-123"
}
```

**Response (200 OK):**
```json
{
  "released": true
}
```

**Status Codes:**
- `200` - Released

---

### POST `/api/internal/transcode/jobs`
**Description:** Create transcode job

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "source": "s3://bucket/cam-001/stream.mp4",
  "cameraId": "cam-001",
  "profiles": [
    {
      "name": "720p",
      "resolution": "1280x720",
      "bitrate": 1500000
    },
    {
      "name": "360p",
      "resolution": "640x360",
      "bitrate": 500000
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "jobId": "job-123",
  "status": "queued"
}
```

**Status Codes:**
- `201` - Job created

---

### GET `/api/internal/transcode/jobs/{id}/status`
**Description:** Get transcode job status

**Path Parameters:**
- `id` - Job ID

**Response (200 OK):**
```json
{
  "jobId": "job-123",
  "status": "running",
  "progress": 45,
  "output": [
    {
      "url": "s3://bucket/output/720p.mp4",
      "profile": "720p"
    }
  ]
}
```

**Status Codes:**
- `200` - Success
- `404` - Job not found

---

### GET `/api/internal/health`
**Description:** System health check

**Response (200 OK):**
```json
{
  "status": "ok",
  "components": {
    "ingest": "ok",
    "transcoder": "ok",
    "storage": "ok"
  }
}
```

**Status Codes:**
- `200` - Healthy

---

## 9. WebSocket Notifications (Realtime)

**Endpoint:** `wss://localhost:8080/ws/notifications?token=<access_token>`

**Message Types:**

### Agent Heartbeat
```json
{
  "type": "agent.heartbeat",
  "agentId": "agent-1",
  "status": "healthy"
}
```

### Stream Available
```json
{
  "type": "stream.available",
  "cameraId": "cam-001",
  "profiles": ["720p", "360p"]
}
```

### Comparison Update
```json
{
  "type": "compare.update",
  "compareSessionId": "cmp-123",
  "status": "ready"
}
```

---

## Common Response Headers

All responses include:
```
Content-Type: application/json
X-Request-ID: <unique-request-id>
```

## Error Response Format

All error responses follow this format:

```json
{
  "error": {
    "code": "RESOURCE_NOT_FOUND",
    "message": "The requested resource was not found",
    "details": {}
  }
}
```

**Common HTTP Status Codes:**
- `200` - OK
- `201` - Created
- `202` - Accepted
- `204` - No Content
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error
