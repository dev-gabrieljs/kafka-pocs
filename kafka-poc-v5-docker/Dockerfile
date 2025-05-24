# Stage 1: build do jar com Maven
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: runtime com OpenJDK
FROM openjdk:17

WORKDIR /app

# Copia o jar específico gerado no build
COPY --from=build /app/target/kafka-poc-v1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
