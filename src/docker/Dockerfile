FROM openjdk:19-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 3001
ENTRYPOINT ["java", "-jar", "/app.jar"]

