package com.example.blogging.service;

import com.example.blogging.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    public User createUser(User user);
    public Optional<User> getUserById(Long id);

    public void deleteUserById(Long id);
    public User updateUser(User user);
    UserDetailsService userDetailsService();


}
