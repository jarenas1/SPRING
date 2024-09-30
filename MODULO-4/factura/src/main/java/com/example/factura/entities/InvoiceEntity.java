package com.example.factura.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"}) SE MANEJARA DESDE EL CONTROLLER
public class InvoiceEntity {

    @Autowired //inyectamos ya que es un bean
    ClientEntity clientEntity;

    //inyectamos valor del properties
    @Value("${invoice.description}")
    private String description;

    @Autowired //Inyectamos el que se creo en el config
    private List<ItemEntity> items;

    public InvoiceEntity() {
    }

    //CREAMOS UN CONSTRUCTOR, QUE SEA DE TIPO POSTCONSTRUCTOR, PARA QUE ACCEDA A LOS ATRIBUTOS DESPUES DE QUE SE CARGUE TODO
    @PostConstruct
    public void init(){
        System.out.println("adios");
        clientEntity.setName("JUAN JOPOLLAS"); //UNA VEZ SE INYECTE EL USUARIO ESTE CONSTRUCTOR ACCEDERA A EL Y LO MODIFICARA, POR ENDE EN POSTMAN SALDRA ESTE NOMBRE
    }

    //CREACION DE METODO QUE EJECUTA ALGO ANTES DE QUE SE CIERRE LA APLICACION O EL SCOPE
    @PreDestroy
    public void destroy(){
        System.out.println("Holaaa"); //AL DESTRUIR EL ALCANCE, EN ESTE CASO SINGLETON (TODO) SE MOSTRARA ESE MENSAJE
    }

    public InvoiceEntity(ClientEntity clientEntity, String description, List<ItemEntity> items) {
        this.clientEntity = clientEntity;
        this.description = description;
        this.items = items;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    //TOTAL DE LA FACTURA

    public Integer getTotal(){
        int total = 0;
        for (ItemEntity item : items){
            total += item.getImporte();
        }
        return total;
    }
}
