package com.juan.DependencyInyection.models;

public class ProductEntity implements Cloneable {
    private Long id;
    private String name;
    private Long price;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    @Override
    public ProductEntity clone() {
        try {
            ProductEntity clone = (ProductEntity) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            //SI NO SE CLONA NOSOTROS CREAMOS UNA NUVA INSTANCIA
            return new ProductEntity(id,name,price);
        }
    }
}
