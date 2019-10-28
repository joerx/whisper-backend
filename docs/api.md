# API Examples

> Postman is for n00bs

-- Lao Tse

## Shouts

### List

```sh
curl localhost:9090/shouts
```

### Get

```sh
curl localhost:9090/shouts/1
```

### Create

```sh
curl -XPOST http://localhost:9090/shouts \
    -H'Content-type: application/json' \
    -d'{"message": "a longer shout", "username": "joerx"}'
```

### Put

```sh
curl -XPUT http://localhost:9090/shouts/5 \
    -H'Content-type: application/json' \
    -d'{"message": "_a fancy shout_", "username": "joerx"}'
```

### Delete

```sh
curl -XDELETE http://localhost:9090/shouts/4
```
