package com.juan.DependencyInyection.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.DependencyInyection.models.ProductEntity;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class ProductRepositoryJson implements IProductRepository{
    //CREAMOS LA LISTA QUE GUARDARA EL JSON
    List<ProductEntity>list;
    public ProductRepositoryJson() {
            ClassPathResource resource = new ClassPathResource("product.json");
            ObjectMapper objectMapper = new ObjectMapper();
        try {
                //Leemos el json
            //CONVERTIMOS EL JSON A JAVA
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), ProductEntity[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<ProductEntity> readAll() {
        return List.of();
    }

    @Override
    public ProductEntity findById(Long id) {
        return list.stream().filter(p ->{
            return p.getId().equals(id);
        }).findFirst().orElseThrow();
    }
}
