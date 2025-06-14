package com.example.Testing.Entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private double balance;
    private String username;
    private String password;

}
