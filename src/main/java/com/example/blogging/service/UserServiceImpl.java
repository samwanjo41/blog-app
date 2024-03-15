package com.example.blogging.service;

import com.example.blogging.entity.User;
import com.example.blogging.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
        private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Blog post with id " + id + " not found");
        }
    }

    @Override
    public User updateUser(User updatedUser) {
        Long id = updatedUser.getId();
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());

            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }
    }

    public UserDetailsService userDetailsService(){
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
