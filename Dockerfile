# Stage 1: Build the application using Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk-slim
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/Orbis-api-1.0.0.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Environment variables for database connection (can be overridden in deployment)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/orbis
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Command to run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
