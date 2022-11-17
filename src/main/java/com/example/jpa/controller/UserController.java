package com.example.jpa.controller;

import com.example.jpa.model.User;
import com.example.jpa.service.UserService;
import com.example.jpa.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // alle Service Methoden benutzen
    // HTTP-Methoden: get, post, put, delete

    @Autowired
    private UserService service;

    @GetMapping  // wird durch Default -Pfad erreicht -@RequestMapping("/users")
    public List<User> getAllMessage() {
        return service.getUsers();
    }
    
    @GetMapping("/user")
    public User getMessage(@RequestParam int id) {

        User u = service.getUserById(id);

        //TODO Exception
        return u;
    }

    @PostMapping("/new-user")
    public User newUser(@RequestBody User user){
        return service.save(user);
    }

}
