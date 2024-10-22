package com.spring.learn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;


@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
//@Where(clause = "active=false") // This annotation is using before Spring Boot 3.x and Java version 17
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String username;

    private String fullName;

    private boolean deleted=false;

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
