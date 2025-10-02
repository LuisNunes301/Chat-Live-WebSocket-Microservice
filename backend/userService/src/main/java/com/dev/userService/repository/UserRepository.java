package com.dev.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.userService.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User save(User user);

    Optional<User> findByNameAndPassword(String name, String password);

    Optional<User> findByEmail(String email);
}
