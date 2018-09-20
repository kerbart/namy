FROM openjdk:8-jdk-alpine
LABEL maintainer="damien@kerbart.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/namy-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} namy.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/namy.jar"]