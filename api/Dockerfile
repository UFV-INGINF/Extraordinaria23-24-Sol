FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pproduction


FROM amazoncorretto:17
EXPOSE 8081
WORKDIR /app
COPY --from=build /app/target/api-dragonball-1.0.0.jar back.jar
ENTRYPOINT ["java","-jar","back.jar"]