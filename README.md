# Whisper Backend

A considerate microblogging-api built with Springboot. Backend application.

## Setup

- Make Postgres run
- Create a new database
- Import [schema.sql](./src/main/resources/schema.sql)

## Configuration

```sh
DATASOURCE_URL=jdbc:postgresql://postgres:5432/whisper
DATASOURCE_USERNAME=postgres
DATASOURCE_PASSWORD=postgres
SERVER_PORT=9090
```

## API Usage

See [docs/api.md](./docs/api.md)
