# Use OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file (we'll name it app.jar after build)
COPY target/*.jar app.jar

# Expose the port Render expects
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]