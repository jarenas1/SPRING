package com.relaciones.relations.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    //RECIBE MUCHAS DIRECCIONES
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //INDICAMOS COMO SE CREARA Y SE ELIMINARAN LOS DATOS
    private List<AdressEntity> adresses; //ALMACENA LOS DATOS

    public ClientEntity() {
    }

    public ClientEntity(Long id, String lastname, String name) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
    }

    public ClientEntity(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<AdressEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressEntity> adresses) {
        this.adresses = adresses;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "adresses=" + adresses +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
