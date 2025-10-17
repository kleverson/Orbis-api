# Stage 1: Build the application using Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Runtime image (correct tag for Render)
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

COPY --from=build /app/target/Orbis-api-1.0.0.jar app.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dbhost:5432/orbis
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENTRYPOINT ["java","-jar","app.jar"]
