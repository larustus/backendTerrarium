package com.example.terrariumappbackend.service;

import com.example.terrariumappbackend.dto.TerrariumDisplayDTO;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.repository.TerrariumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerrariumService {
    private final TerrariumRepository terrariumRepository;

    @Autowired
    public TerrariumService(TerrariumRepository terrariumRepository){
        this.terrariumRepository = terrariumRepository;
    }

    public List<Terrarium> getAllTerrariums(){
        return (List<Terrarium>) terrariumRepository.findAll();
    }
    public Terrarium save(Terrarium terrarium){
        return terrariumRepository.save(terrarium);
    }

    public Optional<Terrarium> findTerrariumById(Integer terrarium_id){
        return terrariumRepository.findById(terrarium_id);
    }

    @Transactional
    public Terrarium changeTemperatureGoal(int terrarium_id, int temperatureGoal){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setTemperature_goal(temperatureGoal);
        return terrariumRepository.save(terrarium);
    }

    @Transactional
    public Terrarium changeHumidityGoal(int terrarium_id, int humidityGoal){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setHumidity_goal(humidityGoal);
        return terrariumRepository.save(terrarium);
    }

    @Transactional
    public Terrarium changeMaxTemp(int terrarium_id, int maxTemp){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setMax_temp(maxTemp);
        return terrariumRepository.save(terrarium);
    }

    @Transactional
    public Terrarium changeMinTemp(int terrarium_id, int minTemp){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setMin_temp(minTemp);
        return terrariumRepository.save(terrarium);
    }

    @Transactional
    public Terrarium changeMaxHum(int terrarium_id, int maxHum){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setMax_hum(maxHum);
        return terrariumRepository.save(terrarium);
    }

    @Transactional
    public Terrarium changeMinHum(int terrarium_id, int minHum){
        Terrarium terrarium = terrariumRepository.findById(terrarium_id).orElseThrow(() -> new RuntimeException("Greenhouse not found with id " + terrarium_id));;
        terrarium.setMin_hum(minHum);
        return terrariumRepository.save(terrarium);
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
        return terrariumDisplayDTO;
    }

}
