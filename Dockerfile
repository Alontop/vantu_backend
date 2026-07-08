
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY . .

RUN mvn clean package -f backend/pom.xml -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]