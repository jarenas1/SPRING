package com.juan.springboot_web.controllers;

import com.juan.springboot_web.models.User;
import com.juan.springboot_web.models.dto.UserDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserRestController {

    //USANDO MAP PARA GENERAR EL JSON
//    @GetMapping("/details2")//DECIMOS QUE ES DE TIPO GET
//    public Map<String,Object> details(){
//        User user = new User("Arenas","Gaviria");//creamos un usuario
//        Map<String, Object> body = new HashMap<>(); //CREAMOS ESTO PARA REPRESENTAR EL JSON
//        body.put("title","Hola Mundo Insertado"); //AÑADIMOS COSAS QUE SE MOSTRARAN AL JSON
////        body.put("name","Juan"); PASAREMOS EL USUARIO CREADO
////        body.put("lastname", "Arenas");
//        body.put("user",user);
//        return body; //DEVOLVEMOS EL JSON
//    }


    //USANDO DTO
    @GetMapping("/details2")
    public UserDTO detailsDTO(){
        //creamos el userDTO
        UserDTO userDTO = new UserDTO();
        //creamos el usuario normal para setearselo
        User user = new User("Arenas","Gaviria");
        //seteamos
        userDTO.setUser(user);
        userDTO.setTitle("JSON CON DTO");
        userDTO.setName(user.getName());
        return userDTO;
    }


    //RETORNAR UNA LISTA
    @GetMapping("/details3")
    public List<User> list(){
        User user = new User("Arenas","Gaviria");
        User user1 = new User("Guzman","Gaviria");
        User user2 = new User("Gaviria","Gaviria");
        //creamos lista para guardarlos
        List<User> users = Arrays.asList(user,user1,user2);
//        users.add(user);
//        users.add(user1);
//        users.add(user2);

        return users;
    }
}
