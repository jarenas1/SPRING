package com.interceptor.INTERCEPTORESHTTP.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class FooController {

    //metodo get
    @GetMapping("/foo")
    public Map<String, String> bar(){ //devolvera un map, este solo tendra un mensaje
        return Collections.singletonMap("message","handlerFoo del controlador");
    }

    //metodo get
    @GetMapping("/bar")
    public Map<String, String> foo(){ //devolvera un map, este solo tendra un mensaje
        return Collections.singletonMap("message","handlerBar del controlador");
    }

    //metodo get
    @GetMapping("/baz")
    public Map<String, String> baz(){ //devolvera un map, este solo tendra un mensaje
        return Collections.singletonMap("message","handlerBaz del controlador");
    }
}
