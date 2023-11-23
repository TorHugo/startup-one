FROM openjdk:17

WORKDIR  /app

COPY target/startup-one-0.0.1-SNAPSHOT.jar /app/startup-one-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/startup-one-0.0.1-SNAPSHOT.jar"]