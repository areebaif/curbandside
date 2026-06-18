FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]