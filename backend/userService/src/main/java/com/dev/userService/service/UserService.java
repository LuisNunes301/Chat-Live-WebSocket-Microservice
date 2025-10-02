
package com.dev.userService.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.userService.models.User;
import com.dev.userService.producer.UserProducer;
import com.dev.userService.repository.UserRepository;

@Service
public class UserService {
    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User save(User userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (userRepository.existsByName(userModel.getName())) {
            throw new IllegalArgumentException("Username já cadastrado");
        }
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

}
