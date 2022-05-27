FROM openjdk:11.0.14-jdk-slim-bullseye
WORKDIR /opt
ADD target/backend-*.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/backend.jar"]
