//package com.example.project.config;
//
//import com.example.project.entity.Role;
//import com.example.project.entity.User;
//import com.example.project.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//
//@Configuration
//public class MyConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(UserService userService){
//        return args -> {
//            userService.saveRole(new Role( "USER"));
//            userService.saveRole(new Role( "ADMIN"));
//            userService.saveRole(new Role( "SUPERADMIN"));
//
//            User a=new User("email@mail.ru", "firstname", "lastname", "password", new ArrayList<>());
//            userService.createUser(a);
//            User b=new User("email2@mail.ru", "firstname2", "lastname2", "password", new ArrayList<>());
//
//            userService.createUser(b);
//
//            userService.assignRole("email@mail.ru","USER");
//            userService.assignRole("email2@mail.ru","USER");
//            userService.assignRole("email@mail.ru","ADMIN");
//        };
//    }
//}
//
