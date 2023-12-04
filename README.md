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

Certainly! Here's the information in Markdown format:

### Postman:

- **Purpose:**
    - Postman is a popular API development and testing tool that simplifies the process of working with APIs. It provides a user-friendly interface for sending HTTP requests to APIs and analyzing the responses.

- **Features:**
    - **Request Builder:** Postman allows users to create various types of HTTP requests, such as GET, POST, PUT, DELETE, etc., through a graphical user interface.
    - **Collections:** Requests can be organized into collections, making it easy to manage and run a series of related API calls.
    - **Environments:** Postman supports the concept of environments, allowing users to define variables and switch between different sets of configurations easily.
    - **Testing:** Postman provides a testing framework that allows users to write test scripts for their API requests to validate the responses.
    - **Automation:** Postman also supports the automation of API testing through the use of collections and the Newman command-line tool.

### Newman:

- **Purpose:**
    - Newman is the command-line companion tool for Postman. It allows users to run Postman collections from the command line, making it easy to integrate API testing into continuous integration (CI) pipelines and automate testing processes.

- **Features:**
    - **Command-line Interface:** Newman provides a command-line interface that allows users to run Postman collections using simple commands.
    - **Automation:** With Newman, users can integrate API testing into their CI/CD workflows, enabling automated testing of APIs as part of the development and deployment process.
    - **Reporting:** Newman generates detailed reports after running a collection, providing insights into the test results.

### Workflow:

1. **Develop and Test in Postman:**
    - Use Postman to create and test API requests interactively.
    - Organize requests into collections and write tests to validate responses.

2. **Automation with Newman:**
    - Integrate Postman collections into automated testing processes using Newman.
    - Run collections from the command line or incorporate them into CI/CD pipelines.
    - Obtain detailed reports on test results.

In summary, Postman is a comprehensive API development and testing tool with a graphical user interface, while Newman is a command-line tool that facilitates the automation and integration of Postman collections into various workflows. Together, they provide a powerful solution for API development, testing, and automation.

```
./integration-test.sh
```


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
