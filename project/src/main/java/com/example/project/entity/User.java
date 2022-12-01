package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique=true)
    private String email;
//    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

//    public User(String email, String username, String firstName, String lastName, String password, Collection<Role> roles) {
//        this.email = email;
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.password = password;
//        this.roles = roles;
//    }
}
