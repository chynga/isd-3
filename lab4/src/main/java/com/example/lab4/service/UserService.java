package com.example.lab4.service;

import com.example.lab4.entity.User;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User updateUser(User user, String id) {

        User updatedUser = new User();
        Optional<User> savedUser = userRepository.findById(id);
        User updateUser = savedUser.get();
        if (savedUser.isPresent()) {
            BeanUtils.copyProperties(user, updateUser, Utils.getNullPropertyNames(user));
        }
        updatedUser = userRepository.save(updateUser);
        return updatedUser;
    }

    public User deleteUser(String id) {

        Optional<User> savedUser = userRepository.findById(id);
        if (!savedUser.isPresent()) {
            throw new Error("user not found");
        }

        User user = savedUser.get();
        userRepository.delete(user);
        return user;
    }

    public List<User> getUsers() {

        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUserById(String id) {

        User user = new User();
        Optional<User> savedUser = userRepository.findById(id);
        if (savedUser.isPresent())
            return savedUser.get();
        return user;
    }
}
