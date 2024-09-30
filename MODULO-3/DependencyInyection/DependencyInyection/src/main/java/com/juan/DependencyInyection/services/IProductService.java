package com.juan.DependencyInyection.services;

import com.juan.DependencyInyection.models.ProductEntity;

import java.util.List;

public interface IProductService {

    List<ProductEntity> findAll();
    ProductEntity findById(Long id);
}
