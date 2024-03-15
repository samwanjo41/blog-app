package com.example.blogging.repository;

import com.example.blogging.entity.Role;
import com.example.blogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
User findByRole(Role role);
Optional<User> findByUsername(String username);
}
