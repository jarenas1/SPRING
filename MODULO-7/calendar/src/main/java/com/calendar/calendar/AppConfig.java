package com.calendar.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//AÑADIREMOS EL INTERCEPTOR A SPRING
@Configuration
public class AppConfig implements WebMvcConfigurer {

    //Inyectamos el interceptor por medio de la interfaz que implementa
    @Autowired
    private HandlerInterceptor calendar;

    //sobre escribimos el metodo de añadir
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //AÑADIMOS EL INTERCEPTOR
        registry.addInterceptor(calendar);//.addPathPatterns("INDICAMOS LAS RUTAS A LAS QUE SE APLICARA")
    }
}
