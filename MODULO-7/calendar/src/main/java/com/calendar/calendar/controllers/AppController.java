package com.calendar.calendar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){ //RECIBIMOS UNA REQUEST HTTP, LA CUA VIENE DEL INTERCEPTOR
        Map<String, Object> message = new HashMap<>();
        message.put("title","Santi sapoperro");
        message.put("time", LocalDateTime.now());
        //devolvemos el mensaje en el map, el mensaje que viene del interceptor
        message.put("pepitoperez",request.getAttribute("message"));
        return ResponseEntity.ok(message);
    }
}
