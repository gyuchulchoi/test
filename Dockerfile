FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/MySpringApp-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} to-do-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/to-do-springboot.jar"]

