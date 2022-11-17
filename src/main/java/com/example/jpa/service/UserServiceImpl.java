package com.example.jpa.service;

import com.example.jpa.dao.UserDAO;
import com.example.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public User save(User user) {
        return dao.save(user);
    }

    @Override
    public List<User> getUsers() {
        return dao.findAll();
    }


    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> u = dao.findById(id);
        if (u.isPresent()) {
            return u.get();
        }
        return null;
    }

    @Override
    public User updateEmail(long id, String newEmail) {
        User user = getUserById(id);
        if (user != null) {
            user.setEmail(newEmail);
            return dao.save(user);
        }
        return null;
    }

    //
    @Override
    public User update(User user) {
        if (user != null) {
            return dao.save(user);// save or update
        }
        return null;
    }

//    public User find(String lastname, String email) {
//        return dao.findByLastnameAndEmail(lastname, email);
 //   }

}
