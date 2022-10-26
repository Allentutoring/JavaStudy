FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# ./gradlew build && java -jar build/libs/Project-0.0.1-SNAPSHOT.jar
# docker build -t allen/java-study-docker .
# docker run -p 8080:8080 allen/java-study-docker

# docker build -t java-study:0.0.1 .
# docker run java-study:0.0.1