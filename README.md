# SpringCrudDTO

A Spring Boot application demonstrating CRUD (Create, Read, Update, Delete) operations using Data Transfer Objects (DTOs) and Spring Data JPA with a MySQL database.

---

## 📈 Stages of Progression

An overview of the evolutionary roadmap and infrastructure progression of the project:

```
┌─────────────────────────┐      ┌─────────────────────────┐      ┌─────────────────────────┐
│     Stage 1: v1.0.0     │      │     Stage 2: v1.1.0     │      │     Stage 3: v1.2.0     │
│   🏠 Local MySQL DB     │ ───► │  ☁️ Aiven Cloud MySQL   │ ───► │  ⚡ Oracle Cloud (OCI)  │
│  (Initial Development)  │      │   (Managed Cloud DB)    │      │  (Enterprise Deployment)│
└─────────────────────────┘      └─────────────────────────┘      └─────────────────────────┘
```

### 📍 Progression Details

* **Stage 1: Local Database (`v1.0.0`)** 🏠
  * Configured and tested with a locally hosted MySQL instance.
  * Established the base architecture: entity mapping, DTO-based separation, soft-delete functionality, and Bruno API test suites.

* **Stage 2: Aiven Cloud Migration (`v1.1.0`)** ☁️
  * Transitioned to a managed MySQL service on **Aiven Cloud**.
  * Enabled remote database access, secured connection handling with SSL/TLS, and introduced environment variable credential management (`${DB_PASSWORD}`).

* **Stage 3: Oracle Cloud Infrastructure (`v1.2.0` — *Current*)** ⚡
  * Migrated the database layer to **Oracle Cloud Infrastructure (OCI)**.
  * Achieved higher reliability, enterprise-grade cloud capabilities, scalable performance, and production readiness.

---

## 🏷️ Version Basis & History

| Version | Milestone | Key Features & Infrastructure |
| :--- | :--- | :--- |
| **`v1.0.0`** | **Local Foundation** | Initial CRUD REST APIs, DTO layer, Hibernate soft-deletion, Local MySQL setup. |
| **`v1.1.0`** | **Aiven Cloud Integration** | Shifted to managed cloud database via Aiven, remote credentials configuration. |
| **`v1.2.0`** | **Oracle Cloud Migration** | Upgraded database infrastructure to Oracle Cloud (OCI) for enterprise deployment. |

---

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Web** (RESTful APIs)
- **Spring Data JPA** (Hibernate)
- **MySQL Connector/J** (Database connection)
- **Maven** (Dependency management and build tool)

## Functionalities

The application exposes a set of RESTful APIs to manage `Student` records via the `StudentController`. It extensively implements the DTO pattern (using `CreateStudentRequestDTO` and `CreateStudentResponseDTO`) to completely decouple the internal database entities from the API layer for creation, retrieval, and updating processes. Record timestamps (`createdAt` and `updatedAt`) are automatically managed by the application service during state changes.

Additionally, the project supports both **hard deletion** and **soft deletion** of records.

### API Testing (Bruno)
The project includes a ready-to-use API testing collection for [Bruno](https://www.usebruno.com/). The endpoint configuration files are conveniently located inside the `src/Bruno Endpoints` directory. You can import this folder directly into your Bruno client to quickly test all the CRUD APIs without needing to configure the requests manually.

### API Endpoints

| HTTP Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/student/create` | Creates a new student record using `CreateStudentRequestDTO`. Returns a `CreateStudentResponseDTO` upon success. |
| `GET` | `/api/student/get?id={id}` | Retrieves a specific active (non-deleted) student by their ID, mapping it to a `CreateStudentResponseDTO`. |
| `GET` | `/api/student/getAll` | Retrieves a list of all active students in the database, mapping them to `CreateStudentResponseDTO`s. |
| `PUT` | `/api/student/update?id={id}` | Updates the information of an existing active student using `CreateStudentRequestDTO`, returning a `CreateStudentResponseDTO`. |
| `DELETE` | `/api/student/delete?id={id}` | Permanently deletes a student record from the database (Hard Delete). |
| `DELETE` | `/api/student/deleteAll` | Permanently deletes all student records from the database. |
| `PATCH` | `/api/student/soft-delete?id={id}` | Marks a student record as deleted by setting a `deleted` flag to true (Soft Delete), hiding it from standard fetch queries. |

## Database Specification

- **Database**: MySQL (compatible with version 8.0+) / Cloud MySQL Instances
- **Schema**: A single `students` table.
- **Table `students` columns**:
  - `id` (BIGINT) – Primary Key, auto‑generated.
  - `first_name` (VARCHAR) – Student's first name.
  - `last_name` (VARCHAR) – Student's last name.
  - `email` (VARCHAR) – Unique email address.
  - `address` (VARCHAR) – Residential address.
  - `phone_no` (VARCHAR) – Contact phone number.
  - `created_at` (TIMESTAMP) – Record creation timestamp.
  - `updated_at` (TIMESTAMP) – Record last update timestamp.
  - `deleted` (BOOLEAN) – Soft‑delete flag, defaults to `false`.
- **Indexes**: Primary key on `id`; unique constraint on `email`.
- **Soft Delete**: Records are not physically removed; setting `deleted` to `true` hides them from standard fetch queries.
- **Configuration**: Set the MySQL connection URL, username, and password in `src/main/resources/application.properties` (or `application.yml`).

## How to Run

1. Ensure you have Java 21 and Maven installed.
2. Ensure you have a running instance of MySQL.
3. Update the `application.properties` (or `.yml`) file inside `src/main/resources` with your MySQL database credentials (url, username, password).
4. Run the application using your IDE or via the command line:
   ```bash
   ./mvnw spring-boot:run
   ```
