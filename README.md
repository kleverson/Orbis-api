# 🏗️ Orbis API

**Orbis API** is a modern RESTful backend built with **Spring Boot** and **Spring Security (JWT)**.  
It provides a robust foundation for authentication, user management, and project tracking — ideal for learning, portfolio demonstration, or real-world application use.

This project was created as part of a professional portfolio to showcase **backend architecture**, **clean API design**, **secure authentication with JWT**, and **automated documentation using Swagger**.

---

## 🚀 Technologies

- Java 17+
- Spring Boot 3+
- Spring Security with JWT
- Spring Data JPA
- Swagger / OpenAPI 3
- BCrypt password hashing

---

## ⚙️ Setup & Run

### 1️⃣ Clone the project
```bash
git clone https://github.com/yourusername/orbis-api.git
cd orbis-api
```

### 2️⃣ Configure Database
```bash

- resouces/application.yml

    spring:
        datasource:
          url: jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
          username: YOUR_DATABASE_USERNAME
          password: YOUR_DATABASE_PASSWORD
          driver-class-name: org.postgresql.Driver

```


### 3️⃣ Build and Run

```bash
./mvnw spring-boot:run
```

### 4️⃣ Access Swagger UI
Open your browser and navigate to:
```
http://localhost:8080/swagger-ui.html
``` 

### 🧱 Project Structure
- `controller/` - REST controllers handling HTTP requests.
- `service/` - Business logic services.
- `repository/` - JPA repositories for database access.
- `model/` - Entity and DTO classes.
- `security/` - JWT authentication and security configuration.


👨‍💻 Author: Kleverson Holanda

📫 Contact: kleverson.holanda@gmail.com
