package com.example.terrariumappbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findUserById(Integer userId){
        return userRepository.findById(userId);
    }

    public User save(User user){
        //temporary
        return userRepository.save(user);
    }
}
