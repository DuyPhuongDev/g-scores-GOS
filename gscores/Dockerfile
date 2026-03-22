FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/gscores-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

CMD ["java", "-jar", "app.jar"]