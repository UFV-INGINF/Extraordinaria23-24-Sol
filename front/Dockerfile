FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package


FROM amazoncorretto:17
EXPOSE 9000
WORKDIR /app
COPY --from=build /app/target/front-dragonball-1.0.0.jar front.jar
ENTRYPOINT ["java","-jar","/front.jar"]