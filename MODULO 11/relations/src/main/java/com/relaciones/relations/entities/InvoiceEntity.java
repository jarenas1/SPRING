package com.relaciones.relations.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long total;

    //RELACION UN CLIENTE TIENE MUCHAS FACTURAS
    @ManyToOne()
    @JoinColumn(name = "id_client_temp")   //    LE DA NOMBRE PERSONALIZADO A LA COLUMNA DE UNION
    private ClientEntity clientEntity;

    public InvoiceEntity() {
    }

    public InvoiceEntity(Long id, String description, Long total) {
        this.id = id;
        this.description = description;
        this.total = total;
    }

    public InvoiceEntity(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public String toString() {
        return "InvoiceEntity{" +
                "clientEntity=" + clientEntity +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                '}';
    }
}
