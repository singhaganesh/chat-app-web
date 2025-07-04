# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar (replace with your jar name)
COPY target/chat-app-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
