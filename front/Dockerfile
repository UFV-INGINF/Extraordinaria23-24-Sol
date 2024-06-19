FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app
COPY . .

# Install curl
RUN apt-get update && apt-get install -y curl

# Install Node.js
RUN curl -sL https://deb.nodesource.com/setup_16.x | bash -
RUN apt-get install -y nodejs

# Verify that Node.js was installed
RUN node -v
RUN npm -v
RUN mvn clean package -Pproduction


FROM amazoncorretto:17
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/target/front-dragonball-1.0.0.jar front.jar
ENTRYPOINT ["java","-jar","front.jar"]