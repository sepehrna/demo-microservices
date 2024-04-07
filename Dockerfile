FROM openjdk:17-jdk-slim

# Working directory in the container
WORKDIR /app

# Application JAR file for the container
COPY target/microservices-1.0.0.jar /app/app.jar

# Expose the port
EXPOSE 8093

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
