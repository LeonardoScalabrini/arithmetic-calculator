# arithmetic-calculator

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=leonardoscalabrini_arithmetic-calculator&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=leonardoscalabrini_arithmetic-calculator)

## Dev quick start ##

1. Install dependencies
````
mvn clean install
````

2. Install Infraestructure
````
docker network create mynet
docker-compose up
````

3. Start locally
````
mvn spring-boot:run
````

## Docker quick start ##

1. To build image
````
mvn package
docker build --tag=arithmetic-calculator:latest .
````

2. Up Infraestructure
````
docker network create mynet
docker-compose up
````

3. Run container locally
````
docker run --name arithmetic-calculator -p 8080:8080 -e SPRING_PROFILE=dockerdev --network=mynet arithmetic-calculator
````

# API Documentation #

## APIs without basic auth

POST /user
{
"email": "email",
"password": "password"
}

## APIs with basic auth

POST /api/v1/basicOperations/addtion?n1=1.0&n2=2.0

POST /api/v1/basicOperations/subtration?n1=1.0&n2=2.0

POST /api/v1/basicOperations/multiplication?n1=1.0&n2=2.0

POST /api/v1/basicOperations/division?n1=1.0&n2=2.0

POST /api/v1/basicOperations/square-root/9

GET /api/v1/basicOperations/random-string

GET /api/v1/records/search?page=0&size=10