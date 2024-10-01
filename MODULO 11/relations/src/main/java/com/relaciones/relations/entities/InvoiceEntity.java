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

    public InvoiceEntity() {
    }

    public InvoiceEntity(Long id, String description, Long total) {
        this.id = id;
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

    @Override
    public String toString() {
        return "InvoiceEntity{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", total=" + total +
                '}';
    }
}
