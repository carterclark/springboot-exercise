# TeamViewer Coding Challenge
Submission by Carter Clark for TeamViewer
## Prerequisites
- Have [docker installed](https://docs.docker.com/engine/install/)
  - If running on windows you may need to enable virtualization in your BIOS settings
- Have [java 17 downloaded](https://www.oracle.com/java/technologies/downloads/#java17)

## Testing
1. open terminal and cd to app directory
2. use command `gradlew clean test --info` to run only tests
   1. tests are also run when building the app with command `./gradlew build`

## Startup with docker compose
1. open terminal and cd to app directory
2. enter `./gradlew build` to pull dependencies and generate new jar file
   1. enter `./gradlew clean build` if rerunning after changing code
3. then enter `docker-compose up`
   1. you may test with an api platform like postman or use the open api links below
4. to stop the application open a separate terminal and enter `docker-compose down` or `ctrl` + `c` in the same terminal

## Optional local development
#### Start postgres in docker
`
docker run -d ^
--name postgres-db ^
-e POSTGRES_USERNAME=root ^
-e POSTGRES_PASSWORD=secret ^
-e POSTGRES_DB=postgres-db ^
-p 5432:5432 ^
postgres
`
#### IntelliJ
1. open repo with [intellij](https://www.jetbrains.com/idea/download/?fromIDE=&section=windows)
2. run `ExerciseApplication`

## OpenApi
- [Swagger UI page](http://localhost:8080/swagger-ui/index.html) 
- [OpenAPI description in json format](http://localhost:8080/v3/api-docs)

### Data objects

- Product
  - ID
  - Name
  - Price
- OrderItem
  - ID
  - Quantity
  - Product
- Order
  - ID
  - Name of customer
  - Array of OrderItems
