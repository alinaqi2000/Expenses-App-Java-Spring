package com.user.user_entry.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "items")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer user_id;
    public String title;
    public Double amount;
    public String date;

    public Item() {
    }

    public Item(String title, Double amount, String date) {
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }
}
