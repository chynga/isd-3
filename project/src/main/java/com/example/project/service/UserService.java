package com.example.project.service;

import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.repo.RoleRepo;
import com.example.project.repo.UserRepo;
import com.example.project.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    public User updateUser(User user, Integer id) {

        User updatedUser = new User();
        Optional<User> savedUser = userRepo.findById(id);
        User updateUser = savedUser.get();
        if (savedUser.isPresent()) {
            BeanUtils.copyProperties(user, updateUser, Utils.getNullPropertyNames(user));
        }
        updatedUser = userRepo.save(updateUser);
        return updatedUser;
    }

    public void deleteUser(int id) {

        Optional<User> savedUser = userRepo.findById(id);
        if (savedUser.isPresent()) {
            User deletedUser = savedUser.get();
            userRepo.delete(deletedUser);
        }
    }

    public List<User> getUsers() {

        List<User> users = userRepo.findAll();
        return users;
    }

    public User getUserById(int id) {

        User user = new User();
        Optional<User> savedUser = userRepo.findById(id);
        if (savedUser.isPresent())
            return savedUser.get();
        return user;
    }

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public void assignRole(String email, String roleName) {
        User user = userRepo.findUserByEmail(email);
        Role role = roleRepo.findRoleByName(roleName);
        user.getRoles().add(role);
    }
}
