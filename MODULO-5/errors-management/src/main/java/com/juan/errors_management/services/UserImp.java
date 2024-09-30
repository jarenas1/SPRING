package com.juan.errors_management.services;

import com.juan.errors_management.models.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImp implements UserService{

    private List<User> users;

    public UserImp (){
        this.users = new ArrayList<>();
        users.add(new User(1L, "Arenas", "Juan"));
        users.add(new User(2L, "E", "Juan"));
        users.add(new User(3L, "I", "Juan"));
        users.add(new User(4L, "O", "Juan"));
        users.add(new User(5L, "U", "Juan"));
    }
    @Override
    public List<User> findAll() {

        return List.of();
    }
//ESTE METODO SE UTILIZABA CUANDO NO ESTABAMOS USANDO EL OPTIONAL
//    @Override
//    public User findById(Long id) {
//        User user = null;
//        for (User u : users){
//            if (u.getId().equals(id)){
//                user = u;
//                break;
//            }
//        }
//        return user;
//    }
//CON OPTIONAL
    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        for (User u : users){
            if (u.getId().equals(id)){
                user = u;
                break;
            }
        }
        return Optional.ofNullable(user); //DEVUELVE UN OPTIONAL LLENO O UNO VACIO
    }
}
