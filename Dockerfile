FROM openjdk:20

WORKDIR /app
COPY target/spring-demo-k6-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "spring-demo-k6-1.0.0-SNAPSHOT.jar"]