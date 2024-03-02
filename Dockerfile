FROM openjdk:17-jdk-alpine
VOLUME build/tmp
ARG JAR_FILE=build/libs/*.jar
COPY build/libs/exercise-0.0.1-SNAPSHOT.jar exercise.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/exercise.jar"]