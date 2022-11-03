package com.example.project.repo;

import com.example.project.entity.Role;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
