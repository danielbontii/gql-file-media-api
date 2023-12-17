# GraphQL Asset Upload API

A GraphQL API that allows users to upload various types of files (images, documents, etc.) and store the associated
metadata.

Technologies Used:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [GraphQL](https://graphql.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Flyway](https://flywaydb.org/)
- [Amazon S3](https://aws.amazon.com/pm/serv-s3/?gclid=EAIaIQobChMIh-717p2VgwMVIJFQBh3YhQtgEAAYASAAEgISofD_BwE&trk=c8974be7-bc21-436d-8108-722e8ab912e1&sc_channel=ps&ef_id=EAIaIQobChMIh-717p2VgwMVIJFQBh3YhQtgEAAYASAAEgISofD_BwE:G:s&s_kwcid=AL!4422!3!645125274431!e!!g!!amazon%20s3!19574556914!145779857032)

## Running the project without docker

* Create a database or use an online database
* Fill all the environment variables
  with [intellij](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) or directly
* Click the run button on intellij

Execute mutations on [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql).
If you have changed the port, replace 8080 with your new port.

See sample mutation below:

```graphql
mutation {
    getPresignedUrl(asset: [
        {
            name: "exampleAsset1",
            extension: "jpg",
            metadata: [
                { key: "author", value: "John Doe" },
                { key: "description", value: "An example image" }
            ]
        },
        {
            name: "exampleAsset2",
            extension: "png",
            metadata: [
                { key: "author", value: "Jane Smith" },
                { key: "description", value: "Another example image" }
            ]
        }
    ]) {
        id
        presignedUrl
    }
}

```

And below is a sample response

```json
{
  "data": {
    "getPresignedUrl": [
      {
        "id": "94cedad6-47a2-495e-91ee-8f4ed3446d53",
        "presignedUrl": "https://gqlassetuploadapi.s3.eu-north-1.amazonaws.com/exampleAsset1?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231217T002243Z&X-Amz-SignedHeaders=host&X-Amz-Expires=299&X-Amz-Credential=ACCESS_KEy%2F20231217%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=3a15ef4860d7a2af043a4eb232396b6a30738b83fec1a31a0a28e70ecee150fa"
      },
      {
        "id": "9c40499c-eaef-4f57-bb33-bc518c13823a",
        "presignedUrl": "https://gqlassetuploadapi.s3.eu-north-1.amazonaws.com/exampleAsset2?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231217T002243Z&X-Amz-SignedHeaders=host&X-Amz-Expires=299&X-Amz-Credential=ACCESS_KEY%2F20231217%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=cfee692521ebd093d7fd2ce2372d6c93a348e7b2ed4430f801d1c22013346d0b"
      }
    ]
  }
}
```

Future Considerations

- Using Amazon Simple Queue to publish new assets and run presignedUrl generation in the background
- Unit and Integration Testing
- User Docker
- Error Handling
- Duplicate management
- API versioning

## Reasons for choosing presignedUrls

- It scales well
- It is the recommended approach

Resources

[https://spring.io/guides/gs/graphql-server/](https://spring.io/guides/gs/graphql-server/)

[https://www.apollographql.com/blog/file-upload-best-practices](https://www.apollographql.com/blog/file-upload-best-practices)




