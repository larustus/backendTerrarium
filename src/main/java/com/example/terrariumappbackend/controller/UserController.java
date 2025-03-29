package com.example.terrariumappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.DTO.UserDTO;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.UserRepository;
import com.example.terrariumappbackend.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

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

    @GetMapping("/{user_id}")
    public UserDTO getUserById(@PathVariable("user_id") Integer user_id) {
        User user = userService.findUserById(user_id)
            .orElseThrow(() -> new IllegalArgumentException("Terrarium not found"));
        return userService.convUserDTO(user);
    }
    


    
}
