# Expense Tracker (MVP)

Simple Expense Tracker API built with Java 17, Spring Boot, JPA and MySQL.

## Tech stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Maven

## Quick start (local)
1. Clone repo:
   ```bash
   git clone https://github.com/bhanurekhagrandhi/expense-tracker.git
   cd expense-tracker
2. Create DB and update src/main/resources/application.properties:

   CREATE DATABASE expense_tracker;

    Set spring.datasource.username and spring.datasource.password.

3. Run:

   mvn clean spring-boot:run


    App runs at http://localhost:8080.

**API Endpoints (MVP)**

- GET /api/expenses — list all expenses
- POST /api/expenses — create expense
- PATCH /api/expenses/{id} — partial update expense
- DELETE /api/expenses/{id} — delete expense
- GET /api/categories — list categories
- POST /api/categories — create category

**Development**

- Unit tests: mvn test
- Build: mvn clean package

