# Stage 1: Build the Spring Boot application using Maven
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Maven wrapper and configuration
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Package the application (skip tests for faster build)
RUN ./mvnw package -DskipTests

# Stage 2: Create the final, lightweight runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

# ✅ Correct path: copy from builder’s /app/target directory
COPY --from=builder /app/target/dictionaryApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]