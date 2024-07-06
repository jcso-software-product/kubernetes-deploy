ARG MSVC_NAME=mvsc-usuario

FROM openjdk:17-jdk-alpine as builder

ARG MSVC_NAME

WORKDIR /app/$MSVC_NAME
COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN \
  apt-get update -y \
  && apt-get install -y

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

ARG MSVC_NAME

WORKDIR /app

COPY --from=builder /app/$MSVC_NAME/target/mvsc-usuario-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001

ENV PORT $PORT_APP

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "mvsc-usuario-0.0.1-SNAPSHOT.jar"]