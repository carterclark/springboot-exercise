# TeamViewer Coding Challenge

## Prerequisites
- Have [docker installed](https://docs.docker.com/engine/install/)
  - If running on windows you may need to enable virtualization in your BIOS settings
- Install [postgres extension in docker](https://hub.docker.com/_/postgres)
  - Search bar in docker app ui and enter `postgres`
- Have [java 17 downloaded](https://www.oracle.com/java/technologies/downloads/#java17)

### Startup with docker compose
1. open terminal and cd to app directory
2. enter `./gradlew build`
3. then enter `docker-compose up`
4. and in a separate`docker-compose down` or `ctrl` + `c` in the same terminal

### Optional local development
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

### OpenApi
#### docker compose
- [Swagger UI page](http://localhost:8000/v3/swagger-ui.html)
- [OpenAPI description in json format](http://localhost:8000/v3/api-docs)
#### running app locally through optional setup
- [Swagger UI page](http://localhost:8080/v3/swagger-ui.html) 
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

