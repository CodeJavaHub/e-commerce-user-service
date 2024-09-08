# Use a base image with Java 21
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Install Gradle
ENV GRADLE_VERSION=8.4
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Copy Gradle wrapper files
COPY gradlew /app/
COPY gradle /app/gradle

# Copy the rest of the project files
COPY . /app

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Create a new stage for the final image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/e-commerce-user-service.jar /app/e-commerce-user-service.jar

# Expose the port the application runs on
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "/app/e-commerce-user-service.jar"]
