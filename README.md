# Whisper Backend

A considerate microblogging-api built with Springboot. Backend application.

## Installation

### Setup

- Get Postgres up and running
- Create a new database, for example `whisper`
- Import [schema.sql](./src/main/resources/schema.sql)
- Use [whisper-compose](https://github.com/joerx/whisper-compose/) to get the full stack running quickly 

### Environment

```sh
DATASOURCE_URL=jdbc:postgresql://postgres:5432/whisper
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=postgres
SERVER_PORT=9092
```

### Running It

- If you're using whisper-compose, use a different port:

```sh
$ SERVER_PORT=9092 ./mvnw spring-boot:run
```

- Test health check endpoint

```sh
$ curl localhost:9092/actuator/health && echo
{"status":"UP"}
```

## Docker

- `dockerfile-maven-plugin` doesn't seem to play nicely with newer Docker versions on macos
- Since [docker-client](https://github.com/spotify/docker-client) is inactive, this looks unlikely to be fixed
- Using a simple Makefile instead:

```sh
$ make clean docker-build
$ make clean docker-push
```

## API Usage

See [API Docs](https://github.com/joerx/whisper-api/blob/master/docs/api.md)
