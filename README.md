# Pierpont

John **Pierpont** Morgan, known as J.P. Morgan, was an influential American banker and financier of the late 19th and early 20th centuries, playing a pivotal role in shaping the U.S. financial system.

Morgan is remembered for consolidating several companies and financial institutions into his corporate empire, including the formation of the United States Steel Corporation and the establishment of the first electric monopoly in the United States, General Electric.

## About Project

The financial transactions project we are developing is a simple solution that allows users to make purchases and financial transactions, taking into account a predefined credit limit.

In this project, users will have the ability to carry out transactions, such as online purchases or payments, as long as the total value of these transactions falls within their assigned credit limit.

## Getting Started
Pierpont is a project to management user transactions

This project use spring boot v3.1.5 with java v17 and maven to management project dependencies

Is possible use docker to provision application and database

### Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)
- [Newman](https://www.npmjs.com/package/newman)

### Installing

To development is necessary compile project to generate relevant components

    mvn clean compile

To generate application artifact

    mvn package

## Running the tests

### Sample Tests
The unit tests ware develop using Junit

JUnit is a popular unit testing framework in Java, and its API provides a set of classes and methods that help developers create and execute unit tests effectively.

The JUnit API includes annotations like `@Test`, which make it easy to define test methods and mark them for automated execution.

Additionally, the JUnit API offers a variety of assert methods, such as `assertEquals` and `assertNotNull`, that allow developers to check expected conditions during test execution, ensuring code quality and robustness.

To execute unit tests is possible use:

    mvn test

### Integration Tests
The integration tests ware develop using Newman and Postman



### Style test

This application use prettier style guide to check style guide use:

    mvn prettier:check

To fix style guide use:

    mvn prettier:write

## Swagger contract

This application have swagger definition with endpoints and models.
In this way, it is possible to import it to your rest client application

## Deployment

To deployment is possible use two ways

- Container Java
- GraalVM compilation

However, it is recommended use docker compose file to configure postgres Database

### Container Java

Compile application to Container Java specific in docker compose the build stage

    pierpont:
        build: .

Then realize the package of application and build of container

    mvn clean package
    docker-compose build --no-cache
    docker-compose up -d

### GraalVM compilation

Compile application using graalvm compiler method, then this process will generate native container image.

    mvn -Pnative spring-boot:build-image

So make sure use image on docker compose file

    pierpont:
        image: pierpont:1.0.0

After, realize build of container with docker compose

    docker-compose up -d
