package com.example.jpa.service;

import com.example.jpa.model.User;

import java.util.List;



public interface UserService {
    User save(User user);

    List<User> getUsers();

    void delete(long id);

    User getUserById(long id);

    User updateEmail(long id, String newEmail);

    //
    User update(User user);
}
