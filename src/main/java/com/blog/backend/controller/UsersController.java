package com.blog.backend.controller;

import com.blog.backend.model.Post;
import com.blog.backend.model.Users;
import com.blog.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userProfile")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;
    @PostMapping("/users")
    public Users postUser(@Valid @RequestBody Users user){
        return usersRepository.saveAndFlush(user);
    }
    @GetMapping("/user")
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }
    @GetMapping("/getUserProfile")
    public Users getUserProfile(Principal principal) {
        return usersRepository.findByUsername(principal.getName()).get();
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(usersRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping(path ="/updateUserProfile",  produces = "application/json")
    @ResponseBody
    public Users updateUserProfile(@Valid @RequestBody Users userDetails,Principal principal) {
        Users users = usersRepository.findByUsername(principal.getName()).get();
        users.setName(userDetails.getName());
        users.setDateOfBirth(userDetails.getDateOfBirth());
        users.setPassword(userDetails.getPassword());
        users.setGender(userDetails.getGender());
        users.setMobileNumber(userDetails.getMobileNumber());
        Users updatedUser = usersRepository.save(users);
        return  updatedUser;
    }


//    @GetMapping("/makePrivate/{username}")
//    public Users makePrivate(@PathVariable(value = "username") String username)
//    {
//        Users u = usersRepository.findByUsername(username).get();
//        u.setUsername(username);
//        u.setIsPrivate(1);
//        usersRepository.saveAndFlush(u);
//
//        return u;
//    }
//
//    @GetMapping("/makePublic/{username}")
//    public Users makePublic(@PathVariable(value = "username") String username)
//    {
//        Users u = usersRepository.findByUsername(username).get();
//        u.setUsername(username);
//        u.setIsPrivate(0);
//
//
//        usersRepository.saveAndFlush(u);
//
//        return u;
//    }

}
