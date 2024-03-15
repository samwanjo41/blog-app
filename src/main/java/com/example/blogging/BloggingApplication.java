package com.example.blogging;

import com.example.blogging.entity.Role;
import com.example.blogging.entity.User;
import com.example.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BloggingApplication.class, args);
    }
    public void  run(String... args){
        User adminAccount =userRepository.findByRole(Role.ADMIN);
        if (null==adminAccount){
            User user = new User();
            user.setUsername("admin@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }

}
