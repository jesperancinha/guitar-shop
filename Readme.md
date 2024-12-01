## Making a query

-   http://localhost:8080/graphiql?path=/graphql

## Query examples

#### Get all guitars

```graphql
query getGuitar {
  guitarById(id: 1) {
    id
    brand
    model
    owner {
      id
      firstName
      lastName
    }
  }
}
```

```shell
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
        "query": "query getGuitar { guitarById(id: 1) { id brand model owner { id firstName lastName } } }"
      }'
```

```graphql
query getGuitar {
  guitarById(id: 1) {
    id
    model
    owner {
      id
      lastName
    }
  }
}
```

```shell
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"{  allUsers { id name age } }"}'
```

```graphql
query getOwner {
  ownerById(id: 1) {
      id
      lastName
  }
}
```

```shell
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
        "query": "query getOwner { ownerById(id: 1) { id lastName } }"
      }'
```

#### All users

```graphql
mutation {
  createUser(name: "Black Raven", age: 30) {
    id
    name
    age
  }
}
```

```graphql
query {
  allUsers {
    id
    name
    age
  }
}
```

```shell
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
        "query": "query allUsers { id name age }"
      }'
```

## Resources

-   https://spring.io/guides/gs/graphql-server
-   https://www.apollographql.com/docs/kotlin/tutorial/04-execute-the-query
-   https://plugins.jetbrains.com/plugin/8097-graphql

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
