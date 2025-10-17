FROM openjdk:17-jdk-slim

LABEL maintainer="Kleverson Holanda <kleverson@example.com>"
LABEL version="1.0"
LABEL description="Orbis-api Spring Boot Application"

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN ./mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/Orbis-api-1.0.0.jar"]
