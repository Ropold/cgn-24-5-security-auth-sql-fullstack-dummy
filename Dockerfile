FROM --platform=linux/amd64 eclipse-temurin:21

EXPOSE 8080

COPY backend/target/my-app.jar my-app.jar

ENTRYPOINT ["java", "-jar", "my-app.jar"]
