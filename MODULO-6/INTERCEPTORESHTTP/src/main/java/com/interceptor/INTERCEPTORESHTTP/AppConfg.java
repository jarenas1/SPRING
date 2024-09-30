package com.interceptor.INTERCEPTORESHTTP;

import com.interceptor.INTERCEPTORESHTTP.interceptors.LoadingTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //marcamos que añadira beans
public class AppConfg implements WebMvcConfigurer {


    @Autowired //Inyectamos la interfaz y abajo indicamos cual de las clases que implementan la interfaz deseamos usar
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;
    //Sobre escribimos el metodo necesario para añadir interceptores

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //AÑADIMOS UNA RUTA A LA QUE VA A ENVOLVER
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo"); //registramos el interceptor que inyectamos

        //SI DESEAMOS EN VEZ DE AÑADIR LAS RUTAS QUE ENVOLVERA, AÑADIR LAS QUE NO ENVOLVERA MODIFICAMOS Y PONEMOS excludePathPattern
    }
}
