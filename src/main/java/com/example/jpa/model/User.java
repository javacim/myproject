package com.example.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@Table(name = "user")  // Tabllenname==user
@Entity // Objekte der Klasse werden persistent(DB)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autoincrement

    private Long id;

    @NotEmpty
    @Column(length = 50)
    private String firstname="";

    @NotEmpty
    @Column(length = 50)
    private String lastname="";
    @Email
    @Column(unique = true, length = 50)
    private String email="";

    private LocalDate dateOfBirth;

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User(String firstname, String lastname, String email, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }


}
