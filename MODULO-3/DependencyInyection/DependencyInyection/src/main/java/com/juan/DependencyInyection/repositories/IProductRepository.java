package com.juan.DependencyInyection.repositories;

import com.juan.DependencyInyection.models.ProductEntity;

import java.util.List;

//CREAMOS TODOS LOS METODOS QUE VA A TENER EL SERVICIO
public interface IProductRepository {

    List<ProductEntity> readAll();

    ProductEntity findById(Long id);
}
