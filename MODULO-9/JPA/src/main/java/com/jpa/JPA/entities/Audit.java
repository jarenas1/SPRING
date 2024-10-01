package com.jpa.JPA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

    //CREADOS PARA EL TEMA DEL CICLO DE VIDA
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
//=================================================

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //CICLOS DE VIDA

    //ASIGNAMOS LA FECHA DE CREACION
    @PrePersist //va a hacer algo antes de guardarse
    public void prePersist(){
        System.out.println("prePersist");
        this.createdAt = LocalDateTime.now(); //SE ASIGNA AL CREAR LA INSTANCIA
    }

    @PreUpdate //va a hacer algo antes de guardarse
    public void preUpdate(){
        System.out.println("preUpdate");
        this.updatedAt = LocalDateTime.now();  //SE ASIGNA CUANDO SE MODIFIQUE
    }
}
