package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleToUser {
    private String email;
    private String roleName;
}
