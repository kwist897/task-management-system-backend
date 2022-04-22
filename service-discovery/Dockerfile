FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /usr/app
COPY ./target/discovery.jar /usr/app/discovery.jar

ENTRYPOINT ["java", "-jar", "discovery.jar"]