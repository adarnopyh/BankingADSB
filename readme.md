# Pet - BankingADSB

A demo **Spring Boot** application for user management and banking operations, featuring:

- **User Registration & Authentication** with roles (`USER`, `ADMIN`).
- **Role-based Access Control** via Spring Security.
- **REST API** & **Server-rendered UI** with Thymeleaf templates.
- **PostgreSQL** data persistence.
- **Docker Compose** for database containerization.
- **Logging** with Logback and request/response filtering.

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Prerequisites](#prerequisites)
5. [Getting Started](#getting-started)
   - [Clone the Repository](#clone-the-repository)
   - [Configure Environment](#configure-environment)
   - [Database Setup](#database-setup)
   - [Run with Docker Compose](#run-with-docker-compose)
   - [Build & Run](#build--run)
6. [Available Endpoints](#available-endpoints)
7. [Frontend Pages](#frontend-pages)
8. [Logging](#logging)
9. [Testing](#testing)
10. [Contributing](#contributing)
11. [License](#license)

---

## Overview

This project demonstrates a simple banking application. Users can register, log in, view their bank account, and (if `ADMIN`) list all users. It uses Spring Boot for the backend, Thymeleaf for the UI, and PostgreSQL for data storage.

## Features

- **User Registration** with form validation
- **Login** and session management
- **Role Management** (`USER`, `ADMIN`)
- **Bank Account** creation with IBAN and balance
- **Server-Side Rendering** for UI pages
- **RESTful APIs** for integration
- **Centralized Logging** with daily rotation
- **Unit Tests** with JUnit 5 & Mockito

## Tech Stack

- **Backend:** Spring Boot 3, Spring MVC, Spring Data JPA, Spring Security
- **Database:** PostgreSQL
- **Templating:** Thymeleaf
- **Build:** Maven
- **Containerization:** Docker Compose
- **Logging:** Logback
- **Testing:** JUnit 5, Mockito

## Prerequisites

- Java 17+ JDK
- Maven 3.6+
- Docker & Docker Compose (for local PostgreSQL)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/BankingADSB.git
cd BankingADSB
```

### Configure Environment

Copy `.env.example` to `.env` and adjust:

```bash
DB_NAME=bankingadsb
DB_USERNAME=bankingadsbuser
DB_PASSWORD=yourpassword
```

### Database Setup

By default the application uses Docker Compose to spin up PostgreSQL:

```bash
docker-compose up -d
```

The compose file creates a `bankingadsb` database and user with the above credentials.

### Run with Docker Compose

Start the database container:

```bash
docker-compose up -d
```

### Build & Run

```bash
mvn clean package
java -jar target/pet-0.0.1-SNAPSHOT.jar
```

Access the app at `http://localhost:8080/`.

## Available Endpoints

| Method | Path            | Description                 |
| ------ | --------------- | --------------------------- |
| GET    | `/api/users`    | List all users (ADMIN only) |
| POST   | `/api/register` | Register new user           |
| POST   | `/login`        | Form-login endpoint         |
| POST   | `/logout`       | Logout                      |

## Frontend Pages

- `/register` → Registration form
- `/login` → Login page
- `/` → Home (list users for ADMIN, user dashboard for USER)

## Logging

Configured in `logback-spring.xml`:

- Writes to `logs/app.log`
- Daily rollover, max 10MB per file
- Retains 7 days or 100MB

## Testing

Run unit tests with Maven:

```bash
mvn test
```

Test coverage reports available at `target/site/jacoco/index.html`.

## Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/foo`)
3. Commit your changes (`git commit -am 'Add foo'`)
4. Push to the branch (`git push origin feature/foo`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

