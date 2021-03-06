# Simple Server File Management Service

Simple Server File Management service finds and manages files on server.
It also finds sub-components of folder such as files and sub-folders.
If a valid file/folder is not found, HttpStatus.NOT_FOUND result is returned.
It deletes files and folders (with its sub-components) from database.
In order to create sample data, generate db sql and populate sql are provided (./kit)

## Technology stack & other Open-source libraries

### Data
- MYSQL - Open-Source Relational Database Management System

### Server - Backend
- JDK 11 - Java™ Platform, Standard Edition Development Kit
- Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications
- Apache Maven - Dependency Management
- Junit(Jupiter) - Unit test

### Libraries and Plugins
- Lombok - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more


### Compile
```
mvn clean
mvn install
```

### Run
```
mvn spring-boot:run
```
