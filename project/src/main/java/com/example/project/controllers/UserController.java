package com.example.project.controllers;

import com.example.project.entity.Role;
import com.example.project.entity.RoleToUser;
import com.example.project.service.UserService;
import com.example.project.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor//instead of autowired
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> show(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("users/register")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.createUser(user));
    }

    @PostMapping ("role/assign-to-user")
    public ResponseEntity<?> assignRole(@RequestBody RoleToUser roleToUser){
        userService.assignRole(roleToUser.getEmail(), roleToUser.getRoleName());
        return ResponseEntity.ok().build();
    }
}
