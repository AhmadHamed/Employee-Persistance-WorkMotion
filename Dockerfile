FROM openjdk:8-jdk-alpine
COPY target/workmotion-task-0.0.1.jar workmotion-task-0.0.1.jar
ENTRYPOINT ["java","-jar","/workmotion-task-0.0.1.jar"]
EXPOSE 8080