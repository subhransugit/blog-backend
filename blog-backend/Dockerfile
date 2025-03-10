# Use an official JDK 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Optional: create a volume to persist logs
VOLUME /tmp

# Copy the JAR file into the container (adjust the path if necessary)
COPY build/libs/blog-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your app will run
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
