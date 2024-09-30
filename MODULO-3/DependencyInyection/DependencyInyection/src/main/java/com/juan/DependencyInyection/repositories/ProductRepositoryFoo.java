package com.juan.DependencyInyection.repositories;

import com.juan.DependencyInyection.models.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository("foo") //Indicamos el nombre con el que será llamado por el qualifier
public class ProductRepositoryFoo implements IProductRepository{
    @Override
    public List<ProductEntity> readAll() {
        return Collections.singletonList(new ProductEntity(1L,"Monitor",600L));
    }

    @Override
    public ProductEntity findById(Long id) {
        return new ProductEntity(id,"Monitor",600L);
    }
}
