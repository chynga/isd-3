package com.example.project.service;

import com.example.project.entity.User;
import com.example.project.repo.UserRepo;
import com.example.project.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {

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
}
