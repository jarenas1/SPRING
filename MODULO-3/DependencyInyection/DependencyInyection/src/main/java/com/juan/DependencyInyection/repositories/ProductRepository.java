package com.juan.DependencyInyection.repositories;

import com.juan.DependencyInyection.models.ProductEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

@SessionScope //INDICAMOS QUE NO ES SINGLETON si no que tiene alcance del request
/*@Primary*/ //INDICAMOS QUE ESTA SERA LA CLASE QUE SE INYECTARA AL INYECTAR LA INTERFAZ, DEBIDO A QUE 2 CLASES ESTAN IMPLEMENTANDO ESTA INTERFAZ
@Repository("product") //le damos el nombre con el que podra ser llamado por el @Qualifier, recordar inicial en MIN
public class ProductRepository implements IProductRepository {

    List<ProductEntity> data; //CREAMOS EL ATRIBUTO DATA

    //INSERTAMOS AL ATRIBUTO INSTANCIAS DE LA ENTIDAD POR MEDIO DE UN CONSTRUCTOR

    public ProductRepository (){
        this.data = Arrays.asList( //EL ASLIST PERMITE AÑADIR VARIOS VALORES SIN TENER QUE ESTAR PONIENDO EL .ADD
                new ProductEntity(1L,"Memoria Ram",300L),
                new ProductEntity(2L, "Intel I9", 600L),
                new ProductEntity(3L, "Teclado Razer", 220L),
                new ProductEntity(4L, "Motherboard", 490L)
        );
    }
    @Override
    public List<ProductEntity> readAll() {
        return List.of();
    }

    @Override
    public ProductEntity findById(Long id){
        return data.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }
}
