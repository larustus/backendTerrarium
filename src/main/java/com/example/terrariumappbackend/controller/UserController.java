package com.example.terrariumappbackend.controller;

import com.example.terrariumappbackend.dto.UserDTO;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Integer userId){
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isPresent()){
            return userService.converToDto(optionalUser.get());
        }else{
            throw new RuntimeException("User not found");
        }
    }
}
