# 1: Maven Build
FROM maven:3.9.6-eclipse-temurin-24-alpine AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2: Jar execution
FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/country-api-service-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]