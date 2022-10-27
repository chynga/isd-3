package com.example.lab4;

import com.example.lab4.entity.User;
import com.example.lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab4Application {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

}
