FROM gradle:6.4.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle npmBuildProd --no-daemon
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]