package com.interceptor.INTERCEPTORESHTTP.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Indicamos que será un componente de spring para que podamos inyectarlo
@Component("timeInterceptor")//indicamos que al inyectar la interfaz, si ponemos este nombre se llamara a este metodo
//DEBEMOS IMPLEMENTAR LA INTERFZ QUE INDICA QUE ES UN INTERCEPTOR
public class LoadingTimeInterceptor implements HandlerInterceptor {

    //Nos permitira mostrar mensajes
    private  static  final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    //SOBRE ESCRIBIMOS LOS METODOS DEL INTERCEPTOR
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    //IMPLEMENTAREMOS LO DEL TIEMPO CUANDO ENTRA Y CUANDO SALE

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //mostrar a que controladr estamos accediendo(casteo del handler)
        HandlerMethod controller = ((HandlerMethod) handler);

        logger.info(("PREHANDLE, ENTRANDO..."+ controller.getMethod().getName()));
        long start = System.currentTimeMillis(); //obtenemos fecha actual en ms

        //podemos añadir datos al request, ya que se envia en ambos metodos, por ende almacenamos start
        request.setAttribute("start",start);

        //FORZAMOS UN TIEMPO DE ESPERA
        Random random = new Random();
        int delay = random.nextInt(500); //obtenemos un numero random
        Thread.sleep(delay);  //Añadimos este numero y generamos un delay

        //SI DEVOLVIERA FALSE, CREAMOS UN ERROR Y SE CORTARIA AHI LA PETICION
        Map<String, String> json = new HashMap();
        //Añadimos error al hashmap
        json.put("Error", "You dont have access to this endpoint");
        //debemos indicar que este json se devolvera, ya que esto no es un restController
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json); //convertimos map a string
        response.setContentType("application/json"); //Indica que se devolvera un JSON
        response.setStatus(401); //indicamos el error que botará
        response.getWriter().write(jsonString); //devolvemos el jsonString
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        long end = System.currentTimeMillis();

        //Traemos el que creamos inicialmente y casteamos
        long start = (long) request.getAttribute("start");

        //Calculamos valor final
        long result = end-start;
        logger.info("tiempo "+result);
        logger.info(("POSTHANDLE, SALIENDO..."));
    }


}
