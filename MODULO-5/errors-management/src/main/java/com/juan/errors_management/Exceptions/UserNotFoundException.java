package com.juan.errors_management.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message); //PASAMOS EL MENSAJE RECIBIDO AL PADRE DEL QUE ESTAMOS HEREDANDO
    }
}
