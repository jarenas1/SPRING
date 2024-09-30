package com.juan.DependencyInyection.services;

import com.juan.DependencyInyection.models.ProductEntity;
import com.juan.DependencyInyection.repositories.IProductRepository;
import com.juan.DependencyInyection.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{


    //INYECCION CON ATRIBUTO
    @Autowired
    @Qualifier("product") //INDICAMOS QUE SE ESTÁ LLAMANDO A LA CLASE NOMBRADA ASÍ, si no se hubiera puesto se hubiera llamado
    //La clase anotada con @Primary
    private IProductRepository productRepository;


    //INYECCION CON SETTER

    //private IProductRepository productRepository;

//    @Autowired   DECIMOS QUE SE VA A LLAMAR AL LA CLASE QUE SE NOMBRO FOO
//    public void setProductRepository(@Qualifier("foo") IProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    //COMO EN EL SERVICES SE APLICA LA LOGICA DEL NEGOCIO ESTAMOS MODIFICANDO EL RESULTADO DE LLAMAR A LOS PRODUCTOS
    public List<ProductEntity> findAll (){
        return productRepository.readAll().stream().map(p -> { //convertimos la lista a stream para poder modificarla y le aplicamos el map que finciona como en js

            Double priceImpuesto = p.getPrice() * 1.25d; //Como p es el objeto iterable sacamos el precio de este y le añadimos un impuesto

            //NO SE CREA GRACIAS AL CLONE
            //ProductEntity newProduct = new ProductEntity(p.getId(),p.getName(), priceImpuesto.longValue()); //Creamos una instancia para modificar
            //p.setPrice(priceImpuesto.longValue()); //seteamos a p el nuevo valor y lo pasamos a Long para que o reciba
            p.setPrice(priceImpuesto.longValue());
            return p;
            //PASAMOS EL PRECIO NUEVO AL CLONE
//            ProductEntity newProd = p.clone();//instanciamos
//            newProd.setPrice(priceImpuesto.longValue()); //pasamos el precio a Long
//            return newProd;
        }).collect(Collectors.toList()); //Para poder retornar p debemos pasarlo de nuevo a una Lista ya que eso es lo que espera el metodo
    }

    public ProductEntity findById(Long id){
        return productRepository.findById(id);
    }
}
