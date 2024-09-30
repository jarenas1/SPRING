package com.juan.errors_management.controllers;

import com.juan.errors_management.Exceptions.UserNotFoundException;
import com.juan.errors_management.models.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class)//indicamos a que error en especifico se le asigna esto
    public ResponseEntity<Error> divisionByZero(Exception ex) { //devolveremos un responseEntity y recibiremos una excepcion

        //CREAMOS EL ERROR QUE DEVOLVEREMOS
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error en division por 0");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); //taambien podemos poner solo 500

        return ResponseEntity.internalServerError().body(error);
        //TAMBIEN SE PUEDE RETORNAR ASI return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error)
    } //devolvemos una respuesta, indicamos que e del tipo servidor e indicamos su cuerpo, en este caso una instancia del molde que creamos


    //OTRO METODO PARA EL 404 NOT FOUND
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFound(Exception ex) {

        Error e = new Error();
        e.setStatus(404);
        e.setMessage(ex.getLocalizedMessage());
        e.setDate(new Date());
        e.setError("error bobo mk");

        return ResponseEntity.status(404).body(e);
    }


    //ERROR DE NumberFormatException DEL CONTROLADOR aña
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Error> numberFormatEx (Exception ex){
        Error e = new Error();
        e.setDate(new Date());
        e.setError("Numero no se puede formatear");
        e.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        e.setMessage(ex.getLocalizedMessage());

        return ResponseEntity.internalServerError().body(e);
    }

    //CREAMOS UN HANDLER PARA CUANDO EL USUARIO ES NULL Y CUANDO EL ROLE ES NULL
    @ExceptionHandler({NullPointerException.class, HttpMessageNotReadableException.class, UserNotFoundException.class})
    public ResponseEntity<Error> UserPointer (Exception ex) {
        //CREAMOS EL ERROR QUE DEVOLVEREMOS
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error, Usuario o rol no existen");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); //taambien podemos poner solo 500

        return ResponseEntity.internalServerError().body(error);
    }
}
