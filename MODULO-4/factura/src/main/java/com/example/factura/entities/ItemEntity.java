package com.example.factura.entities;

public class ItemEntity {

    private ProductEntity productEntity;
    private Integer quantity;

    public ItemEntity() {
    }

    public ItemEntity(ProductEntity productEntity, Integer quantity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //TOTAL DE ITEMS POR PRECIO
    public int getImporte(){
        return  quantity*productEntity.getPrice();
    }
}
