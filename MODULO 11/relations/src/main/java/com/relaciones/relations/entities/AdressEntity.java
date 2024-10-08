package com.relaciones.relations.entities;

import jakarta.persistence.*;

import java.util.Objects;


//ESTA ENTIDAD SERA LA QUE PROOVEA A CLIENT LAS DIRECCIONES MANY

@Entity
@Table(name = "adresses")
public class AdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Integer number;

    public AdressEntity() {
    }

    public AdressEntity(Integer number, String street) {
        this.number = number;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AdressEntity{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdressEntity that = (AdressEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(street, that.street) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number);
    }
}
