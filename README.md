# Api-Key-Microservice

A lightweight **Spring Boot microservice** for issuing, validating, and managing API keys — designed to be dropped in front of other services as a centralized authentication layer.

Built with **Java 21** and **Spring Boot 4.1**, using **PostgreSQL** as the system of record and **Redis** for fast key lookups / rate limiting.

> ⚠️ This repo is under active development. Some sections below (endpoints, request/response payloads) are based on the current dependency stack and may need updating as the API surface evolves — PRs to this README welcome.

---

## ✨ Features

- 🔑 **API key issuance** — generate secure, unique API keys for clients/apps
- ✅ **Key validation** — verify incoming keys before allowing access to protected resources
- ⚡ **Redis-backed caching** — fast key lookups without hitting the database on every request
- 🗄️ **PostgreSQL persistence** — durable storage for keys, ownership, and metadata via Spring Data JPA
- 🧩 **Microservice-ready** — designed to run standalone or alongside other services in a distributed architecture

---

## 🛠 Tech Stack

| Layer          | Technology                     |
|----------------|---------------------------------|
| Language       | Java 21                        |
| Framework      | Spring Boot 4.1.0               |
| Web            | Spring Web MVC                  |
| Persistence    | Spring Data JPA + PostgreSQL    |
| Caching        | Spring Data Redis               |
| Build Tool     | Maven (with `mvnw` wrapper)     |

---

## 📦 Getting Started

### Prerequisites

- Java 21+
- Maven (or use the included `./mvnw` wrapper)
- PostgreSQL instance
- Redis instance

### Clone & Run

```bash
git clone https://github.com/moonmido/Api-Key-Microservice.git
cd Api-Key-Microservice
./mvnw spring-boot:run
```

### Configuration

Set your database and Redis connection details in `src/main/resources/application.properties` (or `application.yml`), e.g.:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/apikeydb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Build

```bash
./mvnw clean package
java -jar target/*.jar
```

---

## 📚 API Overview

> Update this section with your actual controller endpoints/paths as the API stabilizes.

| Method | Endpoint            | Description                     |
|--------|----------------------|----------------------------------|
| POST   | `/api/keys`          | Generate a new API key           |
| GET    | `/api/keys/{key}`    | Validate/lookup an existing key  |
| DELETE | `/api/keys/{key}`    | Revoke an API key                |

---

## 🗺️ Roadmap

- [ ] Key expiration / TTL support
- [ ] Per-key rate limiting
- [ ] Usage analytics per key
- [ ] Docker Compose setup (app + Postgres + Redis)
- [ ] OpenAPI/Swagger docs

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome. Feel free to open a PR or issue.

