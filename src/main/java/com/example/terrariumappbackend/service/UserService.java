package com.example.terrariumappbackend.service;

import com.example.terrariumappbackend.dto.TerrariumDTO;
import com.example.terrariumappbackend.dto.UserDTO;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserDTO converToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword_hash(user.getPassword_hash());
        userDTO.setTerrariumData(user.getTerrariumList().stream()
                .map(terrarium -> new TerrariumDTO(terrarium.getId(), terrarium.getName()))
                .collect(Collectors.toList()));
        return userDTO;
    }
}
