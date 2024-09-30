package com.juan.errors_management.controllers;

import com.juan.errors_management.Exceptions.UserNotFoundException;
import com.juan.errors_management.models.domain.User;
import com.juan.errors_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/app")
    public String index(){
        int value = 100/0; //DARA UNDEFINED Y UN ERROR ARITHMETHICEXCEPTION
        return "okController";
    }

    @GetMapping("/aña")
    public String index1(){
        int value = Integer.parseInt("100x"); //NO SE PODRA FORMATEAR DEBIDO A LA X
        return "okController";
    }

    //INYECTAMOS LOS USUARIOS Y TODO DEL SERVICE

    @Autowired
    private UserService userService;
//CUANDO NO ESTABAMOS USANDO EL OPTIONAL
//    @GetMapping("show/{id}")
//    public User getById(@PathVariable Long id){
//        User u = userService.findById(id);
//        if (u == null){ //Verificamos si el usuario es nulo o no para devolver el error
//            throw new UserNotFoundException("ERROR, EL USUARIO NO EXISTE"); //Enviamos el mensaje de error al metodo
//        }   //Indicamos que es un nuevo error
//        System.out.println(u.getLastname());
//        return u;
//    }

    @GetMapping("show/{id}")
    public User getById(@PathVariable Long id){
        User u = userService.findById(id).orElseThrow(()-> new UserNotFoundException("ERROR, EL USUARIO NO EXISTE"));
        //ESTO LO QUE HACE ES, Y GRACIAS AL .ORELSETHROW PODEMOS DECIR QUE SI ENCUENTRA EL USER LO DEVUELVA O SI NO TIRE LA EXCEPCION
        //NO SE USA EL IF GRACIAS A LO DE ARRIBA
        System.out.println(u.getLastname());
        return u;
    }
}
