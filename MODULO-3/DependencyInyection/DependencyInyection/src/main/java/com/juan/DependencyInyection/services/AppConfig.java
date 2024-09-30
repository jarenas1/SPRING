package com.juan.DependencyInyection.services;

import com.juan.DependencyInyection.repositories.IProductRepository;
import com.juan.DependencyInyection.repositories.ProductRepository;
import com.juan.DependencyInyection.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    //CREACION DE UN COMPONENTE
    @Primary //Indicamos que es primary, asi cada vez que se llame a la interfaz de los repositorys se va a inyectar este componente
    @Bean
    IProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}
