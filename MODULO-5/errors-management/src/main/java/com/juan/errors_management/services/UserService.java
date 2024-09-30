package com.juan.errors_management.services;

import com.juan.errors_management.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    //User findById(Long id); SE COMENTO YA QUE USAREMOS EL OPTIONAL, este permite devolver un objeto O UNA EXCEPCION
    Optional<User> findById(Long id);
}
