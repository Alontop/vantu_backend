# Usamos una imagen de Maven más moderna y soportada
FROM maven:3.8.5-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests

# Usamos la imagen oficial de Eclipse Temurin (la recomendada para Java 17)
FROM eclipse-temurin:17-jre-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]