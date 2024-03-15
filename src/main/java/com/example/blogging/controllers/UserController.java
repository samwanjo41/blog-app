package com.example.blogging.controllers;

import com.example.blogging.entity.User;
import com.example.blogging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/v1/user")
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/api/v1/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/api/v1/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        return  userService.updateUser(updateUser);
    }

    @DeleteMapping("/api/v1/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
