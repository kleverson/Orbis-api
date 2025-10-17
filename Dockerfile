# Stage 1: Build
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project (skip tests)
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

# Copy the generated JAR (match your artifactId and version)
COPY --from=build /app/target/Orbis-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Database environment variables (can be overridden in Render dashboard)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://<DB_HOST>:5432/orbis
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
