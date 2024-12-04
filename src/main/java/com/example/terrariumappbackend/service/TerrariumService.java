package com.example.terrariumappbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.DTO.TerrariumDisplayDTO;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.TerrariumRepository;
import com.example.terrariumappbackend.repository.UserRepository;

@Service
public class TerrariumService {
    private final TerrariumRepository terrariumRepository;
    private final UserRepository userRepository;

    @Autowired
    public TerrariumService(TerrariumRepository terrariumRepository, UserRepository userRepository){
        this.terrariumRepository = terrariumRepository;
        this.userRepository = userRepository;
    }

    public List<TerrariumDisplayDTO> getTerrarriumsByUserId(Integer user_id){
        
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user_id));
        List<Terrarium> terrariums = terrariumRepository.findByUser(user);
        return terrariums.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public TerrariumDisplayDTO convertToDto(Terrarium terrarium){
        TerrariumDisplayDTO terrariumDisplayDTO = new TerrariumDisplayDTO();
        terrariumDisplayDTO.setId(terrarium.getId());
        terrariumDisplayDTO.setName(terrarium.getName());
        terrariumDisplayDTO.setTemperature_goal(terrarium.getTemperature_goal());
        terrariumDisplayDTO.setHumidity_goal(terrarium.getHumidity_goal());
        terrariumDisplayDTO.setMax_temp(terrarium.getMax_temp());
        terrariumDisplayDTO.setMin_temp(terrarium.getMin_temp());
        terrariumDisplayDTO.setMax_hum(terrarium.getMax_hum());
        terrariumDisplayDTO.setMin_hum(terrarium.getMin_hum());
        terrariumDisplayDTO.setWater_time(terrarium.getWater_time());
        return terrariumDisplayDTO;
    }

}
