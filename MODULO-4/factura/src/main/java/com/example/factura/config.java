package com.example.factura;
//CLASE DE CONFIGURACION, TRAEREMOS DATOS DE UN PROPERTIES Y CREAREMOS UN BEAN CON @BEAN

import com.example.factura.entities.ItemEntity;
import com.example.factura.entities.ProductEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:data.properties") //indicamos el nombre del properties
public class config {

    //CREAMOS EL BEAN DE items PARA FACTURA(Lista de items) PARA PODER INYECTARLO

    @Bean
    List<ItemEntity> items(){

        //Creamos productos para añadir a la lista
        ProductEntity productEntity1 = new ProductEntity("camara",1200);
        ProductEntity productEntity2 = new ProductEntity("ps5",8200);


        //añadimos a una lista items creados, se pasa el producto y la cantidad ara crear una instancia de item y se guardan en una lista
        return Arrays.asList(new ItemEntity(productEntity1,2),new ItemEntity(productEntity2,4));
    }
    //COMO TENEMOS 2 BEANS DEL MISMO TIPO, DEBEMOS INDICAR CUAL SE VA A INYECTAR EN LA FACTURA, POR ESO USAMOS EL PRIMARY
    @Primary
    @Bean
    List<ItemEntity> itemsOffice(){

        //Creamos productos para añadir a la lista
        ProductEntity productEntity1 = new ProductEntity("lapicero",1200);
        ProductEntity productEntity2 = new ProductEntity("cuaderno",6200);
        ProductEntity productEntity3 = new ProductEntity("impresora",600);
        ProductEntity productEntity4 = new ProductEntity("monitor",4300);


        //añadimos a una lista items creados, se pasa el producto y la cantidad ara crear una instancia de item y se guardan en una lista
        return Arrays.asList(new ItemEntity(productEntity1,2),new ItemEntity(productEntity2,4),new ItemEntity(productEntity3,1),new ItemEntity(productEntity4,6));
    }
}
