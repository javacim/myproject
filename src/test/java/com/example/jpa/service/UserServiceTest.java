package com.example.jpa.service;

import com.example.jpa.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        User u1 = new User("Maxi","Meier","meier@web.de", LocalDate.now());
        service.save(u1);

    }

    @Test
    void getUsers() {
        System.out.println(service.getUsers());
    }
}