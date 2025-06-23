# Teleport Tracking Number API 🛰️

**Teleports unique tracking numbers for shipments. Stateless, thread-safe, and deployable with a single command.**

---

## 🚀 Features

- `GET /core/next-tracking-number` — Generate regex-safe & unique tracking numbers
- Stateless SHA-256 hashing + microsecond timestamp
- Validated input: all query params are required
- OpenAPI/Swagger documentation
- Docker-ready for free cloud deployment (Render)

---


---

## ⚙️ Prerequisites

- Java 17+ (local dev)
- Maven (if building locally)
- Docker (for production build)
- Optional: Render CLI or GitHub push access

---

## 🛠️ Local Setup

1. Clone:
   ```bash
   git clone https://github.com/akashmitra1611/teleport.git
   cd teleport

2. Build:
```bash
    ./mvnw clean package -DskipTests
```

3. Run:
```bash
    java -jar target/teleport-0.0.1-SNAPSHOT.jar
```

4. Test from Swagger
   http://localhost:8001/track/swagger-ui/index.html

| Field                    | Value                                  |
| ------------------------ | -------------------------------------- |
| origin\_country\_id      | `MY`                                   |
| destination\_country\_id | `ID`                                   |
| weight                   | `1.234`                                |
| created\_at              | `2025-06-23T12:00:00+08:00`            |
| customer\_id             | `de619854-b59b-425e-9db4-943979e1bd49` |
| customer\_name           | `RedBox Logistics`                     |
| customer\_slug           | `redbox-logistics`                     |


5. Test on Cloud Environment
   https://teleport-fy80.onrender.com/swagger-ui/index.html#/track-controller/getNextTrackingNumber



