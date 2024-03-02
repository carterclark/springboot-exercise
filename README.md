# TeamViewer Coding Challenge

## Prerequisites
- Have docker installed
- [Install postgres extension in docker](https://hub.docker.com/_/postgres)
  - Search bar in docker app ui and enter `postgres`
- Have java downloaded

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



### Startup
1. open terminal and cd to app directory
2. enter `./gradlew build`
3. then enter `docker-compose up`
4. and in a separate`docker-compose down` or `ctrl` + `c` in the same terminal

#### Optional local development
1. open repo with intellij
2. enter `./gradlew build` in terminal
3. build docker postgres with terminal command: `docker-compose.yml -p exercise up -d postgres`


### TODO
- Service class
  - Order
  - OrderItem
- Error handling on all requests
- Unit testing
- Utilize open api
- Change orderItemEntity in OrderEntity class to array

