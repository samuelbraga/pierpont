# Pierpont

Pierpont is a project to management user transactions

## Getting Started

This project use spring boot v3.0.1 with java v17 and maven to management project dependencies

Is possible use docker to provision application and database

### Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)

### Installing

To development is necessary compile project to generate relevant components

    mvn clean compile

To generate application artifact

    mvn package

## Running the tests

### Sample Tests

To execute unit tests is possible use:

    mvn test

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
