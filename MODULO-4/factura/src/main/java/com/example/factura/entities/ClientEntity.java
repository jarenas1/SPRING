package com.example.factura.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component //añadimos al contenedor para poder inyectarlo
@SessionScope //INDICAMOS EL ALCANCE
@JsonIgnoreProperties({"targetSource","advisors"}) //IGNORAMOS ERRORES DEL PROXY RESIDUALES
public class ClientEntity {

//    Comentamos e inyectamos desde el properties
//    private String name;
//    private String lastname;

    @Value("${client.name}") //NOMBRE DEL ATRIBUTO EN EL PROPERTIES
    private String name;

    @Value("${client.name}")
    private String lastname;

    public ClientEntity() {
    }

    public ClientEntity(String lastname, String name) {
        this.lastname = lastname;
        this.name = name;
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
}
