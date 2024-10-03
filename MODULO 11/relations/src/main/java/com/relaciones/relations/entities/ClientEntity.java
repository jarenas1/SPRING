package com.relaciones.relations.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    //PERSONALIZACION PARA QUE NO SE CREE UNA TABLA NUEVA, SI NO UNA COLUMNA EN ESTA TABLA QUE CONTENGA LAS FK
//    @JoinColumn(name = "fk_contains")
    //CONFIGURACION PARA PERSONALIZAR LA TABLA QUE SE CREA POR DEFECTO
//    @JoinTable(name = "tabla_union",  //DA NOMBRE A LA TABLA
//    joinColumns = @JoinColumn(name = "id_client")      //DA NOMBRE A LA COLUMNA QUE ALMACENA ESTE ID
//    inverseJoinColumns = @JoinColumn(name = "id_adress"),      //DA NOMBRE A COLUMNA QUE ALMACENA ID DE LA OTRA TABLA
//    uniqueConstraints = @UniqueConstraint(columnNames = {"id_adress"})    //INDICA LAS COLUMNAS QUE DEBEN SER UNICAS
//    )
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) //INDICAMOS COMO SE CREARA Y SE ELIMINARAN LOS DATOS Y EL TIPO DE FETCH
    private List<AdressEntity> adresses; //ALMACENA LOS DATOS


    //BIDIRECCIONAL CON FACTURAS ONETOMANY
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "clientEntity") //mappedByIndica como se instancio esta clase en el many
    private List<InvoiceEntity> invoices = new ArrayList<>(); //se inicializa aca o en el constructor


    //ONETOONE BIDIIRECCIONAL  //esta es la clase que se alimenta, por ende condifuramos todo aca
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "clientEntity")
    private ClientDetails clientDetails;


    public ClientEntity() {
        //SE INICIALIZA LA LISTA, TAMBIEN SE PUEDE INICIALIZAR NORMALMENTE
        this.adresses = new ArrayList<>();
    }

    public ClientEntity(Long id, String lastname, String name) {
        //inicializamos la lista
        this();
        this.id = id;
        this.lastname = lastname;
        this.name = name;
    }

    public ClientEntity(String name, String lastname) {
        //inicializamos la lista
        this(); //se llama constructor vacio, si metieramos atributos llamaria el constructor que los contenga
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

    public List<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "adresses=" + adresses +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", invoices=" + invoices +
                ", clientDetails=" + clientDetails +
                '}';
    }
}
