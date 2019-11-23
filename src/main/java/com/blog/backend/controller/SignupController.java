package com.blog.backend.controller;

import com.blog.backend.model.Users;
import com.blog.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    UsersRepository usersRepository;

    @PostMapping(path = "/sendingData" , consumes="application/json")
    public String signUp(@RequestBody Users user) throws Exception {
        System.out.println("Sign up service is working");
        if(usersRepository.findByUsername(user.getUsername()).isPresent())
        throw new Exception("Not a unique username");
        System.out.println("Adding a value");
        usersRepository.save(user);

        return "\"Success\"";
    }
}
