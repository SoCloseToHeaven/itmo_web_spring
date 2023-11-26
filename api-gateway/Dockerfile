FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle bootJar --no-daemon

FROM amazoncorretto:17
WORKDIR /
COPY --from=build /src/build/libs/*.jar /api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]
