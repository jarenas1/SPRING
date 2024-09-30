package com.juan.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/details")//DECIMOS QUE ES DE TIPO GET
    public String details(Model model){//CREAMOS MODEL PARA ENVIAR COSAS A LA VISTA
        model.addAttribute("title","Hola Mundo Insertado"); //AÑADIMOS COSAS QUE SE ENVIARAM
        model.addAttribute("name","Juan");
        model.addAttribute("lastname", "Arenas");
        return "details";
    }
}
