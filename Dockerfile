#FROM gradle:8.8-jdk21 AS builder
#WORKDIR /opt/app
#COPY settings.gradle ./settings.gradle
#COPY build.gradle ./build.gradle
#COPY src ./src
#RUN gradle build --no-daemon --stacktrace

FROM openjdk:21-jdk-slim
WORKDIR /opt/app
EXPOSE 8080
#COPY --from=builder /opt/app/build/libs/*.jar /opt/app/*.jar
COPY build/libs/project_restaurants.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]