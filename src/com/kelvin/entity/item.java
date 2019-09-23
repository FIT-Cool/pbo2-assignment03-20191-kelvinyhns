package com.kelvin.entity;

import java.time.LocalDate;

public class item {
    public String id;
    public String name;
    public category category;
    public LocalDate date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.kelvin.entity.category getCategory() {
        return category;
    }

    public void setCategory(com.kelvin.entity.category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  name ;
    }
}