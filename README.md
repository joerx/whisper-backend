# Whisper Backend

A considerate microblogging-api built with Springboot. Core backend service.

## Installation

### Setup

Get Postgres up and running, create a new database, for example `whisper_backend`. Make sure you have an instance of
[whisper-auth](https://github.com/joerx/whisper-auth) running to obtain authentication tokens.

Tip: Use [whisper-compose](https://github.com/joerx/whisper-compose/) to get the full stack up and running quickly. 

### Environment

The following env vars can be set to affect the behaviour of the system:

```sh
DATASOURCE_URL=jdbc:postgresql://localhost:5432/whisper_backend
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=postgres
SERVER_PORT=9092
SPRING_PROFILES_ACTIVE=dev
```

### Usage

The Maven profile will run the application in dev mode on the default port `9091`:

```sh
$ ./mvnw spring-boot:run
```

To on an alternative port (e.g. if compose is already hogging the default port):

```sh
$ SERVER_PORT=19091 ./mvnw spring-boot:run
```

To on another profile:

```sh
$ SPRING_PROFILES_ACTIVE=test ./mvnw spring-boot:run
```

Health check endpoint:

```sh
$ curl localhost:9091/actuator/health && echo
{"status":"UP"}
```

## Docker

The `dockerfile-maven-plugin` doesn't seem to play nicely with newer Docker versions on macOS. Since 
[docker-client](https://github.com/spotify/docker-client) is inactive, this looks unlikely to be fixed.

We're providing a simple Makefile instead:

```sh
$ make clean docker-build
$ make clean docker-push
```

## API Usage

See [API Docs](https://github.com/joerx/whisper-api/blob/master/docs/api.md)
