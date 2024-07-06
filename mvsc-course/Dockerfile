FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/mvsc-course
COPY ./pom.xml /app
COPY ./mvsc-course/.mvn ./.mvn
COPY ./mvsc-course/mvnw .
COPY ./mvsc-course/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./mvsc-course/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/mvsc-course/target/mvsc-course-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "mvsc-course-0.0.1-SNAPSHOT.jar"]