# product-kotlin-hexagonal
Product service with kotlin and gradle using hexagonal pattern 


## Project Structure

These plugins are required plugins for Spring Kotlin projects
```groovy
plugins {
	id 'org.springframework.boot' version '3.2.1'
	id 'io.spring.dependency-management' version '1.1.4'
	id 'org.jetbrains.kotlin.jvm' version '1.9.21'
	id 'org.jetbrains.kotlin.plugin.spring' version '1.9.21'
}
```


## Running locally

### Swagger UI
[Swagger UI](http://localhost:8080/swagger-ui/index.html)
### MongoDB
```shell
export MONGODB_VERSION=6.0-ubi8
docker run --name mongodb -d mongodb/mongodb-community-server:$MONGODB_VERSION
docker run --name mongodb -d -p 27017:27017 mongodb/mongodb-community-server:$MONGODB_VERSION
```

```shell
docker run --name mongodb -d -p 27017:27017 mongodb/mongodb-community-server:6.0-ubi8

docker run --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=TechInnovative -e MONGO_INITDB_ROOT_PASSWORD=TechInnovative -v $(pwd)/data:/data/db mongodb/mongodb-community-server:6.0-ubi8 
```

Connection String: mongodb://user:pass@mongodb

```yaml
version: '3'
services:
  myapplication:
    image: mongodb/mongodb-community-server:6.0-ubi8
    environment:
      - CONN_STR=mongodb://user:pass@mongodb
    command: '/bin/bash -c "sleep 5; mongosh $$CONN_STR --eval \"show dbs;\""'
    depends_on:
      - mongodb
  mongodb:
    image: mongodb/mongodb-community-server:6.0-ubi8
    environment:
      - MONGO_INITDB_ROOT_USERNAME=user
      - MONGO_INITDB_ROOT_PASSWORD=pass
    volumes:
      - type: bind
        source: ./data
        target: /data/db
```