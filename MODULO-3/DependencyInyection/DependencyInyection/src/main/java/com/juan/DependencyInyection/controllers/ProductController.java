package com.juan.DependencyInyection.controllers;

import com.juan.DependencyInyection.models.ProductEntity;
import com.juan.DependencyInyection.services.IProductService;
import com.juan.DependencyInyection.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Indicamos que sera un controlador
@RequestMapping("/products") //indicamos la ruta para acceder al controlador
public class ProductController {

    //PARA PODER ACCEDER A LOS METODOS DEL SERVICE LO INSTANCIAMOS
   //ProductService productService = new ProductService();

    //ELIMINAMOS LO DE ARRIBA E INYECTAMOS LA DEPENDENCIA
    //@Autowired SE COMENTA YA QUE VAMOS A INYECTAR POR MEDIO DE UN CONTRUCTOR
    private IProductService productService;
    //MANERA CORRECTA DE AÑADIR EL SERVICE

    //CONSTRUCTOR

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //LEER TODOS

    @GetMapping("/read") //INDICAMOS QUE ES UNA PETICION TIPO GET
    public List<ProductEntity> List(){
//        ProductService productService = new ProductService(); MALA PRACTICA
        return productService.findAll();
    }

    //BUSCAR POR ID, PARAMOS ID COMO PATHVARIABLE
    @GetMapping("/find/{id}")
    public ProductEntity findById(@PathVariable Long id){
//        ProductService productService = new ProductService();
        return productService.findById(id);
    }
}
