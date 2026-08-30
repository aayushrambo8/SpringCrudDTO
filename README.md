# SpringCrudDTO

A Spring Boot application demonstrating CRUD (Create, Read, Update, Delete) operations using Data Transfer Objects (DTOs) and Spring Data JPA with a MySQL database.

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Web** (RESTful APIs)
- **Spring Data JPA** (Hibernate)
- **MySQL Connector/J** (Database connection)
- **Maven** (Dependency management and build tool)

## Functionalities

The application exposes a set of RESTful APIs to manage `Student` records via the `StudentController`. It extensively implements the DTO pattern (using `StudentRequestDTO` and `StudentResponseDTO`) to completely decouple the internal database entities from the API layer for creation, retrieval, and updating processes.

Additionally, the project supports both **hard deletion** and **soft deletion** of records.

### API Testing (Bruno)
The project includes a ready-to-use API testing collection for [Bruno](https://www.usebruno.com/). The endpoints configuration files are conveniently located inside the `src/Bruno Endpoints` directory. You can import this folder directly into your Bruno client to quickly test all the CRUD APIs without needing to configure the requests manually.

### API Endpoints

| HTTP Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/student/create` | Creates a new student record using `StudentRequestDTO`. Returns a `StudentResponseDTO` upon success. |
| `GET` | `/api/student/get?id={id}` | Retrieves a specific active (non-deleted) student by their ID, mapping it to a `StudentResponseDTO`. |
| `GET` | `/api/student/getAll` | Retrieves a list of all active students in the database, mapping them to `StudentResponseDTO`s. |
| `PUT` | `/api/student/update?id={id}` | Updates the information of an existing active student using `StudentRequestDTO`, returning a `StudentResponseDTO`. |
| `DELETE` | `/api/student/delete?id={id}` | Permanently deletes a student record from the database (Hard Delete). |
| `DELETE` | `/api/student/deleteAll` | Permanently deletes all student records from the database. |
| `PATCH` | `/api/student/soft-delete?id={id}` | Marks a student record as deleted by setting a `deleted` flag to true (Soft Delete), hiding it from standard fetch queries. |

## How to Run

1. Ensure you have Java 21 and Maven installed.
2. Ensure you have a running instance of MySQL.
3. Update the `application.properties` (or `.yml`) file inside `src/main/resources` with your MySQL database credentials (url, username, password).
4. Run the application using your IDE or via the command line:
   ```bash
   ./mvnw spring-boot:run
   ```
