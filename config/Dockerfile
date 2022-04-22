FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /usr/app
COPY ./target/config.jar /usr/app/config.jar

ENTRYPOINT ["java", "-jar", "config.jar"]