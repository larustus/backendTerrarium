package com.example.terrariumappbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.entity.Pin;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.PinRepository;
import com.example.terrariumappbackend.repository.UserRepository;

@Service
public class PinService {
    private final PinRepository pinRepository;
    private final UserRepository userRepository;

    @Autowired
    public PinService(PinRepository pinRepository, UserRepository userRepository){
        this.pinRepository = pinRepository;
        this.userRepository = userRepository;
    }

    public List<Pin> getPinsByUserId(Integer user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user_id));
        List<Pin> pins = pinRepository.findByUser(user);
        return pins;
    }

    public List<Pin> getAllFreePinsForUserByFunction(Integer user_id, String function){
        List<Pin> pins = pinRepository.findFreePinsByUserAndFunction(user_id, function);
        return pins;
    }

    public List<Pin> getAllFreeDSPinsForUser(Integer user_id, String prefix){
        List<Pin> pins = pinRepository.findPinDSByUser(user_id, prefix);
        return pins;
    }

}
