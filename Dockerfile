FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY . .

WORKDIR /app/bookathlon-backend

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/bookathlon-backend/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
