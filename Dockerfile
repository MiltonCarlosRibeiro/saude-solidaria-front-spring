# syntax=docker/dockerfile:1
ARG JDK_VERSION=21


FROM maven:3.9.8-eclipse-temurin-${JDK_VERSION} AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -ntp -q -DskipTests validate
COPY src ./src
RUN mvn -B -ntp -DskipTests package


FROM eclipse-temurin:${JDK_VERSION}-jre-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 3000
USER spring
ENTRYPOINT ["java","-jar","/app/app.jar"]