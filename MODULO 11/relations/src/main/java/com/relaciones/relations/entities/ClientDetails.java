package com.relaciones.relations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clients_details")
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean premium;

    private Integer points;

    //RELACION ONETOONE
    @JoinColumn(name = "client_id")
    @OneToOne
    private ClientEntity clientEntity; //se instancia la clase del otro lado

    public ClientDetails() {
    }

    public ClientDetails(Long id, Integer points, boolean premium) {
        this.id = id;
        this.points = points;
        this.premium = premium;
    }

    public ClientDetails(ClientEntity clientEntity, Integer points, boolean premium) {
        this.clientEntity = clientEntity;
        this.points = points;
        this.premium = premium;
    }

    public ClientDetails(Integer points, boolean premium) {
        this.points = points;
        this.premium = premium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "id=" + id +
                ", premium=" + premium +
                ", points=" + points +
                '}';
    }
}
