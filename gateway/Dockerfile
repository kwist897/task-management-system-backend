FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /usr/app/
COPY ./target/gatewlsay.jar /usr/app/gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]
