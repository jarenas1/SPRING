package com.juan.springboot_web.controllers;

import com.juan.springboot_web.models.User;
import com.juan.springboot_web.models.dto.ParamDTO;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/var")
public class PathVariableController {

    @GetMapping("/baz/{message}") //INDICAMOS QUE VA A ENTRAR UN PARAMETRO ENTRANTE
    public ParamDTO baz(@PathVariable String message){ //Tomamos lo que entra en la ruta y lo asignamos RECORDAR QUE SE DEBEN LLAMAR IGUAL EL QUE RECIBE Y EL PARAMETRO QUE ENTRA
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setMessage(message);
        return paramDTO;
    }

    //CON VARIAS VARIABLES ENTRANTES
    @GetMapping("/mix/{message}/{code}")
    public Map<String,Object> mixPath(@PathVariable String message, @PathVariable Integer code){ //SE DEBEN LLAMAR LOS PARAMETROS EN EL MISMO ORDEN EN EL QUE SE DECLARAN EN LA RUTA
        Map<String,Object>json = new HashMap<>();
        json.put("message", message);
        json.put("id",code);

        return json;
    }


    //METODO QUE CREE UN USUARIO USANDO POST
    @PostMapping("/create")
    public User create (@RequestBody User user){ //INDICAMOS QUE EL CUERPO DEL POST VA A SER LO QUE SE TOMARA COMO user
        //guardaremos el usuario en la db
        return user;
    }

}
