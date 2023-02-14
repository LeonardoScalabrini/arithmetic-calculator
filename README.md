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