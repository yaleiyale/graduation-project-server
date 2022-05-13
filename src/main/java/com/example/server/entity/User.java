package com.example.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "user_password")
    private String userPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer uid) {
        this.userId = uid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}