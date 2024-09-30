package com.juan.springboot_web.models;

public class User {

    private String name;
    private String lastName;

    public User(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }

    public User() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
