# Scenario

Suppose we are developing a user management system. In this system, instead of deleting a user directly from the database, we can choose to mark the user as “deleted”. This approach, instead of performing a delete operation in the database, updates the value of a `deleted` field to “true” or “false”, indicating that the user has been deleted. This method is called “soft delete”.

If we only want to deal with users who have not been deleted and we want to query these users through `Repository`, the `@SQLRestriction` annotation (known as `@Where` in previous versions), available as of Spring Boot 3, comes into play. With this anotation, we can ensure that only undeleted users are retrieved in our queries.

## Installation

- First, we define the database library docker-compose configuration.

```yaml
        
version: '3.8'
services:

  mysql:
    image: mysql:8.0
    container_name: mysql
    restart: always
    environment:
      - MYSQL_DATABASE=userdb
      - MYSQL_ROOT_PASSWORD=password
    ports:
      - '3306:3306'
```

- Second, we define the database library and JPA library we will use.
```xml
        
         <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```

- Third, we perform the necessary configurations of this database.
```properties

##MySQL Config
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_DB:userdb}?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=${MYSQL_USERNAME:root}
spring.datasource.password=${MYSQL_PASSWORD:password}


##JPA Config
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

```

## Usage

- First, we use the @SQLDelete annotation since we will perform an update operation on the delete operation

```java
@SQLDelete(sql = "update users set deleted=true where id=?")
```

- As a second operation, if we want to attract only non-deleted users, we use the @Where annotation (Optional)
```java

@SQLRestriction(value = "deleted=false")
//@Where(clause = "active=false") // This annotation is using before Spring Boot 3.x and Java version 17
```
## Responses

 - Using @SQLRestriction


![image](https://github.com/user-attachments/assets/a43c6c8f-e825-498b-8432-4fb22cc1bbe5)


- Not-Using @SQLRestriction


![image](https://github.com/user-attachments/assets/8ecfa131-bee4-4dcd-81d4-d62671787114)
