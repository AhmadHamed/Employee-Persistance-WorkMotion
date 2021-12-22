# Employee-Persistance-WorkMotion

Workmotion [Spring Boot](http://projects.spring.io/spring-boot/) Employee app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

At first, let's run 
```shell
mvn clean install 
```
this will start installing the needed dependencies, index the project & clean it

Then there are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.workmotion.task.WorkmotionTaskApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Running tests
```shell
mvn test
```

## To view the Api Contract
```shell
mvn spring-boot:run
```
navigate to your browser and open the following [Link](http://localhost:8080/swagger-ui/index.html)

## Building the container
First lets package our app and create an excutable jar
```shell
mvn clean package
```
then run docker commands as follows
```shell
docker build --tag=workmotion-employee:latest
```
```shell
docker run -p8080:8080 --tag=workmotion-employee:latest
```
