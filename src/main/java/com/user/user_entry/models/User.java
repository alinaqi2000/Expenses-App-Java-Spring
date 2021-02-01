package com.user.user_entry.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;
    public String email;
    public String password;
    public String image_url;
    public String token;
    public String created_at;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String image_url, String created_at) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image_url = image_url;
        this.created_at = created_at;
    }

    public User(Integer id, String name, String email, String password, String image_url, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image_url = image_url;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
