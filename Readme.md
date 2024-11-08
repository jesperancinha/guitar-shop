

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

```graphql
query getOwner {
  ownerById(id: 1) {
      id
      lastName
  }
}
```


## Resources

- https://spring.io/guides/gs/graphql-server
- https://www.apollographql.com/docs/kotlin/tutorial/04-execute-the-query
- https://plugins.jetbrains.com/plugin/8097-graphql
