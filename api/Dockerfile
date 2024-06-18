FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package


FROM amazoncorretto:17
EXPOSE 9001
WORKDIR /app
COPY --from=build /app/target/api-dragonball-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]