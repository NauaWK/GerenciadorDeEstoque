# build -> maven e jdk
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# run -> jre
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# instala dockerize
RUN apk add --no-cache wget && \
    wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz && \
    tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-v0.6.1.tar.gz

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["dockerize", "-wait", "tcp://db:3306", "-timeout", "300s", "java", "-jar", "app.jar"]
