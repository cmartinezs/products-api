# Products RESTful API

## Build and execution instructions

### Test and Jacoco coverage report
```bash
./gradlew test
```
The Jacoco coverage report is on the default path `build/reports/jacoco/test/html/index.html`

### Run
```bash
./gradlew bootRun
```
This command executes the rest-api application locally available on port 8080

### Use of available endpoints
1.- Get all products
```bash
curl --location --request GET 'localhost:8080/products'
```
2.- Get product by `sku`
```
curl --location --request GET 'localhost:8080/products/FAL-881952283'
```
3.- Post a new product
```bash
curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sku": "qwertyuijkio",
    "name": "The name",
    "brand": "The brand",
    "price": 23.0,
    "principalImage": "http://www.asd.com/una-imagen.jpg",
    "otherImages": ["http://www.asd.com/otra-imagen.jpg"]
}'
```
4.- Put a product by `sku`
```bash
curl --location --request PUT 'localhost:8080/products/FAL-881952283' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sku": "FAL-881952283",
    "name": "Bicicleta Baltoro Aro 26",
    "brand": "Jeep",
    "size": "STD",
    "price": 559990.0,
    "principalImage": "https://falabella.scene7.com/is/image/Falabella/881952283_1",
    "otherImages": [
        "https://falabella.scene7.com/is/image/Falabella/881952283_2"
    ]
}'
```
5.- Delete product by `sku`
```bash
curl --location --request DELETE 'localhost:8080/products/FAL-8406270'
```

## Architectural and Technological decisions

The architecture of this api is based on the hexagonal architecture, which is one of the considered clean architectures to be able to separate the application, the infrastructure that supports it and the business domain, in this way independent of the technologies that are used to to expose or to persist the business is one and can be reused since it is ageno to these.

To expose the endpoints we used the rest controllers available in spring-web through their annotations for this stereotype.

The persistence was implemented using JPA in conjunction with the CrudRespository interfaces available in Spring-Jpa.

As a database, H2 was chosen, since it is an in-memory database that is easy to configure.
