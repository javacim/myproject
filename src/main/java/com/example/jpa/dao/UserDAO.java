package com.example.jpa.dao;

import com.example.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository - definiert Standard-CRUD Methoden
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {


   // User findByLastnameAndEmail(String lastname, String email);

    // Spring JPA generiert und implementiert Methode nach Methoden-Namen: findByLastnameAndEmail
    // und erzeugt Query : SELECT * FROM user WHERE lastname=? AND email=?


}
