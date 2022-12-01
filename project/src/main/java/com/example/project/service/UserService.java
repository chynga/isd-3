package com.example.project.service;

import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.repo.RoleRepo;
import com.example.project.repo.UserRepo;
import com.example.project.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

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
        System.out.println(roleName);
        user.getRoles().add(role);
    }
}
