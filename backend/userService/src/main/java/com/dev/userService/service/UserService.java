
package com.dev.userService.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.userService.models.User;
import com.dev.userService.repository.UserRepository;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Transactional
    public User save(User userModel) {
        userModel = userRepository.save(userModel);
        return userModel;
    }
}
