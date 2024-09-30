package com.jpa.JPA.DTO;

public class PersonDto {

    //SE CREAN LOS ATRIBUTOS QUE SE DEVOLVERAN AL USUARIO
    private String name;
    private String lastname;

    public PersonDto() {
    }

    public PersonDto(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
