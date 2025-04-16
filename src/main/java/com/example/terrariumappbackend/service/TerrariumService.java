package com.example.terrariumappbackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.DTO.TerrariumDTO;
import com.example.terrariumappbackend.DTO.TerrariumDisplayDTO;
import com.example.terrariumappbackend.DTO.TerrariumRaspberry;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.TerrariumRepository;
import com.example.terrariumappbackend.repository.UserRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TerrariumService {
    private final TerrariumRepository terrariumRepository;
    private final UserRepository userRepository;

    @Autowired
    public TerrariumService(TerrariumRepository terrariumRepository, UserRepository userRepository){
        this.terrariumRepository = terrariumRepository;
        this.userRepository = userRepository;
    }

    public TerrariumDisplayDTO getTerrarriumDTOById(Integer terrarium_id){
        
        Terrarium terrarium = terrariumRepository.findById(terrarium_id)
                .orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + terrarium_id));
        // User user = userRepository.findById(user_id)
        //         .orElseThrow(() -> new RuntimeException("User not found with ID: " + user_id));
        // List<Terrarium> terrariums = terrariumRepository.findByUser(user);
        TerrariumDisplayDTO terrariumDTO = convertToDto(terrarium);
        return terrariumDTO;
            // .map(this::convertToDto)
            // .collect(Collectors.toList());
    }

    public List<TerrariumDisplayDTO> getAllTerrariumDTOsByUserId(Integer user_id){
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
        terrariumDisplayDTO.setWater_period(terrarium.getWater_period());
        terrariumDisplayDTO.setCurrent_temp1(terrarium.getCurrent_temp_1());
        terrariumDisplayDTO.setCurrent_temp2(terrarium.getCurrent_temp_2());
        terrariumDisplayDTO.setCurrent_hum(terrarium.getCurrent_hum());
        terrariumDisplayDTO.setUser_id(terrarium.getUser().getId());
        terrariumDisplayDTO.setTemperature_thermostat(terrarium.getTemperature_thermostat());
        terrariumDisplayDTO.setLast_update(terrarium.getLast_update());
        return terrariumDisplayDTO;
    }

    public List<TerrariumDTO> getTerrariumIdsByUserId(Integer user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user_id));
        List<Terrarium> terrariums = terrariumRepository.findByUser(user);
        return terrariums.stream()
            .map(this::convertToSimpleDto)
            .collect(Collectors.toList());
    
    }

    public TerrariumDTO convertToSimpleDto(Terrarium terrarium){
        TerrariumDTO terrariumDTO = new TerrariumDTO();
        terrariumDTO.setId(terrarium.getId());
        terrariumDTO.setName(terrarium.getName());
        return terrariumDTO;
    }

    public boolean updateReadings(Integer terrarium_id, Float current_temperature_1, Float current_temperature_2, Float current_hum, Float temperature_thermostat){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id)
            .orElseThrow(() -> new IllegalArgumentException("Terrarium not found"));
        terrarium.setCurrent_temp_1(current_temperature_1);
        terrarium.setCurrent_temp_2(current_temperature_2);
        terrarium.setCurrent_hum(current_hum);
        terrarium.setTemperature_thermostat(temperature_thermostat);
        terrarium.setLast_update(ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toLocalDateTime());
        terrariumRepository.save(terrarium);
        return true;
    }

    public TerrariumRaspberry getTerrariumForRaspberry(Integer terrariumId){
        Terrarium terrarium = terrariumRepository.findById(terrariumId)
            .orElseThrow(() -> new IllegalArgumentException("Terrarium not found"));
        TerrariumRaspberry terrariumRaspberry = new TerrariumRaspberry();
        terrariumRaspberry.setId(terrariumId);
        terrariumRaspberry.setTemperature_goal(terrarium.getTemperature_goal());
        terrariumRaspberry.setHumidity_goal(terrarium.getHumidity_goal());
        terrariumRaspberry.setMax_temp(terrarium.getMax_temp());
        terrariumRaspberry.setMin_temp(terrarium.getMin_temp());
        terrariumRaspberry.setMax_hum(terrarium.getMax_hum());
        terrariumRaspberry.setMin_hum(terrarium.getMin_hum());
        terrariumRaspberry.setWater_time(terrarium.getWater_time());
        terrariumRaspberry.setWater_period(terrarium.getWater_period());
        return terrariumRaspberry;
    }

}
