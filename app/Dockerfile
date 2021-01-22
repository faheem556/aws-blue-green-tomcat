FROM openjdk:16-slim AS base
EXPOSE 8080
WORKDIR /app

FROM maven:3.6.3-openjdk-16-slim AS build
WORKDIR /usr/src/app
COPY pom.xml ./
RUN /usr/local/bin/mvn-entrypoint.sh \
    mvn package -Dmaven.test.skip=true -Dcheckstyle.skip=true -Dmaven.javadoc.skip=true --fail-never
COPY . .
RUN mvn package -Dmaven.test.skip=true -Dcheckstyle.skip=true -Dmaven.javadoc.skip=true

FROM base AS final
COPY --from=build /usr/src/app/target/*.jar ./
ENTRYPOINT ["java","-jar","FunfactApplication.jar"]