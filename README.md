# product-kotlin-hexagonal
Product service with kotlin and gradle using hexagonal pattern 

## Gradle
```
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| Name               | Role                 | Consumable? | Resolveable? | Description                             |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| api                | Declaring            |      no     |      no      | This is where you should declare        |
|                    | API                  |             |              | dependencies which are transitively     |
|                    | dependencies         |             |              | exported to consumers, for compile.     |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| implementation     | Declaring            |      no     |      no      | This is where you should                |
|                    | implementation       |             |              | declare dependencies which are          |
|                    | dependencies         |             |              | purely internal and not                 |
|                    |                      |             |              | meant to be exposed to consumers.       |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| compileOnly        | Declaring compile    |     yes     |      yes     | This is where you should                |
|                    | only                 |             |              | declare dependencies                    |
|                    | dependencies         |             |              | which are only required                 |
|                    |                      |             |              | at compile time, but should             |
|                    |                      |             |              | not leak into the runtime.              |
|                    |                      |             |              | This typically includes dependencies    |
|                    |                      |             |              | which are shaded when found at runtime. |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| runtimeOnly        | Declaring            |      no     |      no      | This is where you should                |
|                    | runtime              |             |              | declare dependencies which              |
|                    | dependencies         |             |              | are only required at runtime,           |
|                    |                      |             |              | and not at compile time.                |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| testImplementation | Test dependencies    |      no     |      no      | This is where you                       |
|                    |                      |             |              | should declare dependencies             |
|                    |                      |             |              | which are used to compile tests.        |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| testCompileOnly    | Declaring test       |     yes     |      yes     | This is where you should                |
|                    | compile only         |             |              | declare dependencies                    |
|                    | dependencies         |             |              | which are only required                 |
|                    |                      |             |              | at test compile time,                   |
|                    |                      |             |              | but should not leak into the runtime.   |
|                    |                      |             |              | This typically includes dependencies    |
|                    |                      |             |              | which are shaded when found at runtime. |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
| testRuntimeOnly    | Declaring test       |      no     |      no      | This is where you should                |
|                    | runtime dependencies |             |              | declare dependencies which              |
|                    |                      |             |              | are only required at test               |
|                    |                      |             |              | runtime, and not at test compile time.  |
+--------------------+----------------------+-------------+--------------+-----------------------------------------+
```

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