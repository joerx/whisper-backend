# API Examples

> Postman is for n00bs

-- Lao Tse

## Auth

Token is required for all requests, except the token API itself and some management endpoints. To get a token:

```sh
$ curl -XPOST localhost:9091/token -d'{"client_id": "12345", "client_secret": "hellosecret"}' -H"Content-type: application/json"
```

Using `jq`:

```sh
$ TOKEN= $(curl -XPOST localhost:9091/token -d'{"client_id": "12345", "client_secret": "hellosecret"}' -H"Content-type: application/json" | jq -r '.token') 
```

## Shouts

List shouts:

```sh
$ curl -H"Authorization: Bearer $TOKEN" localhost:9091/shouts
```

Get shout by id:

```sh
curl -H"Authorization: Bearer $TOKEN" localhost:9091/shouts/1
```

Create new shout:

```sh
curl -XPOST http://localhost:9091/shouts \
    -H"Authorization: Bearer $TOKEN" \
    -H"Content-type: application/json" \
    -d'{"message": "a longer shout", "username": "john"}'
```

Update (replace) shout:

```sh
curl -XPUT http://localhost:9091/shouts/5 \
    -H"Authorization: Bearer $TOKEN" \
    -H"Content-type: application/json" \
    -d'{"message": "_a fancy shout_", "username": "joerx"}'
```

Delete shout:

```sh
curl -H"Authorization: Bearer $TOKEN" -XDELETE http://localhost:9091/shouts/4
```
