FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle --no-daemon build --parallel

FROM amazoncorretto:17
WORKDIR /
COPY --from=build /src/build/libs/*.jar /auth-service.jar
ENTRYPOINT ["java","-jar","auth-service.jar"]
