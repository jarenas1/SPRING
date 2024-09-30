package com.juan.springboot_web.models.dto;

import com.juan.springboot_web.models.User;

public class UserDTO {
    private String title;
    private User user;
    private String name; //PARA TRAERLO EN EL CONTROLADOR DEL OBJETO ORIGINAL

    public UserDTO(String title, User user, String name) {
        this.title = title;
        this.user = user;
        this.name = name;
    }

    public UserDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
