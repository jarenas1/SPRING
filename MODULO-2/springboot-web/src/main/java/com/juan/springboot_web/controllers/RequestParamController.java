package com.juan.springboot_web.controllers;

import com.juan.springboot_web.models.dto.ParamDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    //DEBEMOS RECIBIR UN DATO DEL USUARIO, ENTONCES USAMOS @REQUESTPARAM
    //EN EL NAVEGADOR SE PONE LA RUTA Y SE ASIGNA UN VALOR POR MEDIO DE ?variable= valor
    @GetMapping("/foo")
    public ParamDTO foo(@RequestParam(required = false, defaultValue = "hi my name is elber") String message){ //indica que se recibe algo llamado message de tipo String
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setMessage(message);
        return  paramDTO;
    //SI EL USUARIO NO ENVIA EL PARAMETRO LE SALE UN ERROR, PARA EVITAR ESTO PODEMOS DECIRLE AL REQUEST QUE NO SEA OBLIGARIRIO
        //ASI: @RequestParam(required=false, defaultValue = "xxxx")
    }


    //AHORA LO HACERMOS RECIBIENDO VARIOS PARAMETROS

    @GetMapping("/bar")
    public ParamDTO bar (@RequestParam String text, @RequestParam Integer code){ //DECIMOS QIE ENTRARAN ESTOS 2 PARAMETROS
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setMessage(text);
        paramDTO.setCode(code);

        return paramDTO;
    }
    //AHORA PARA PASARLE LOS PARAMETROS AL FINAL DE LA RUTA PONEMOS ?name=value y para separar ponemos &



    //OTRA FORMA DE HACER ESTO ES USANDO SERVLET
    @GetMapping("/request")
    public ParamDTO request(HttpServletRequest request){
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setMessage(request.getParameter("message")); //POR DEFECTO RD NULL, ASI QUE NO SE AÑADE NADA NO PASA NADA

        //EL CODE AL PARSEARSE NO PUEDE SER NULL, ASI QUE DEBEMOS DARLE UN VALOR POR DEECTO CON UN TRYCATCH
        Integer code = 0;
        try {
            //Si no le permite obtener el parametro va a seguir siendo 0 por defecto
            code = Integer.parseInt(request.getParameter("code")); //VIENEN POR DEFECTO EN STRING, LO PASAMOS A INT
        }catch (NumberFormatException e){

        }
        paramDTO.setCode(code);
        return paramDTO;
    }
}
