package com.example.testfive.service;

import com.example.testfive.dao.UserRepository;
import com.example.testfive.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void createUser(String username){
        User user = new User();
        user.setUsername(username);
        save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
}
