# CWS Parent Portal API

The CWS Parent Portal API provides RESTful services for the CWS Parent Portal.

## Configuration

The CWS Parent Portal API requires the following environment variables:

- DB_USER -- the database username
- DB_PASSWORD -- the database password
- DB_JDBC_URL -- the database URL in Java Database Connectivity format

The Docker env-file option provides a convenient method to supply these variables. These instructions assume an env file
called .env located in the current directory. The repository contains a sample env file called env.sample.

Further configuration options are available in the file config/parentportal_config.yml.

## Installation

### Prerequisites

1. PostgreSQL database 9.4

### Using Docker

The CWS Parent Portal API is available as a Docker container from the following registry:

    297353277856.dkr.ecr.us-west-2.amazonaws.com/parent-portal-api

Run the application with Docker using a command like this:

    % docker run --env-file=.env -p 8080:8080 -v log:/var/log/cws-parent-portal-api parent_portal_api_image

Containers are tagged with the short Git commit hash from the GitHub repository.

### Database Initialization

The CWS Parent Portal API database must be initialized prior to starting the first time. To initialize, execute the
following command:

    % docker run --env-file=.env parent_portal_api_image db init /usr/local/etc/cws-parent-portal-api/cws-parent-portal-api.yml

The first time the application starts it will initialize the database schema during the boot process.

## Development Environment

### Prerequisites

1. Source code, available at GitHub
1. Java SE 8 development kit
1. PostgreSQL database 9.4
1. Configuration environment variables, see Configuration section
1. Docker 1.10

### Building

Use the gradlew command to execute the shadowJar task:

    % ./gradlew shadowJar

The shadow jar (aka fat jar) containing the application and its dependencies will be located at
build/libs/parentportal-all.jar.

### Database Initialization

Execute the build, then use the db init command to initialize your development database:

    % java -jar build/libs/parentportal-all.jar db migrate config/parentportal_config.yml

### Database Seeding

Use your favorite database tool to execute the SQL files in src/main/resources/db/seeds.

### Development Server

Use the gradlew command to execute the run task:

    % ./gradlew run

This will run the server on your local machine, port 8080.

### Testing

Tests that access the database utilize the src/test/resources/hibernate.cfg.xml configuration file. Edit this file to
utilize a local testing database.

Use the gradlew command to execute the test task:

    % ./gradlew test

### Building Docker Container

The continuous delivery server builds images for the container registry but developers can build local containers with
the following command:

    % docker build -t parent_portal_api .

This results in a local docker image in a repository called parent_portal_api with the tag latest.