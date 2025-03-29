package com.example.terrariumappbackend.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.service.TerrariumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.terrariumappbackend.DTO.TerrariumDTO;
import com.example.terrariumappbackend.DTO.TerrariumDisplayDTO;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.repository.TerrariumRepository;
import com.example.terrariumappbackend.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/terrariums")
public class TerrariumController {
    private final TerrariumService terrariumService;
    private final TerrariumRepository terrariumRepository;
    private final UserRepository userRepository;

    @Autowired
    public TerrariumController(TerrariumService terrariumService, TerrariumRepository terrariumRepository, UserRepository userRepository){
        this.terrariumService = terrariumService;
        this.terrariumRepository = terrariumRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{user_id}")
    public List<TerrariumDisplayDTO> getTerrarriumsByUserId(@PathVariable Integer user_id) {
        return terrariumService.getTerrarriumsByUserId(user_id);
    }

    @GetMapping("/user/id/{user_id}")
    public List<TerrariumDTO> getTerrariumDTOsByUserId(@PathVariable Integer user_id){
        return terrariumService.getTerrariumIdsByUserId(user_id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTerrarium(@RequestBody TerrariumDisplayDTO terrariumDTO) {
        try {
            // Fetch the User entity using user_id from the DTO
            User user = userRepository.findById(terrariumDTO.getUser_id())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + terrariumDTO.getUser_id()));

            // Map TerrariumDisplayDTO to Terrarium entity
            Terrarium terrarium = new Terrarium();
            terrarium.setName(terrariumDTO.getName());
            terrarium.setTemperature_goal(terrariumDTO.getTemperature_goal());
            terrarium.setHumidity_goal(terrariumDTO.getHumidity_goal());
            terrarium.setMax_temp(terrariumDTO.getMax_temp());
            terrarium.setMin_temp(terrariumDTO.getMin_temp());
            terrarium.setMax_hum(terrariumDTO.getMax_hum());
            terrarium.setMin_hum(terrariumDTO.getMin_hum());
            terrarium.setWater_time(terrariumDTO.getWater_time());
            terrarium.setWater_period(terrariumDTO.getWater_period());
            terrarium.setCurrent_temp_1(terrariumDTO.getCurrent_temp1());
            terrarium.setCurrent_temp_2(terrariumDTO.getCurrent_temp2());
            terrarium.setCurrent_hum(terrariumDTO.getCurrent_hum());
            terrarium.setTemperature_thermostat(terrariumDTO.getTemperature_thermostat());
            terrarium.setUser(user);

            // Save the Terrarium entity
            Terrarium savedTerrarium = terrariumRepository.save(terrarium);

            return ResponseEntity.ok("Terrarium saved with ID: " + savedTerrarium.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving terrarium: " + e.getMessage());
        }
    }
    
    @PutMapping("update/{terrarium_id}")
    public ResponseEntity<String> updateReadings(@PathVariable Integer terrarium_id, @RequestParam Float current_temperature1, @RequestParam Float current_temperature2, @RequestParam Float current_hum, @RequestParam Float temperature_thermostat) {

        boolean isUpdated = terrariumService.updateReadings(terrarium_id, current_temperature1, current_temperature2, current_hum, temperature_thermostat);
        if (isUpdated) {
            return ResponseEntity.ok("Readings updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Terrarium not found or update failed.");
        }
    }
    
    @PutMapping("/{id}/name")
    public Terrarium updateName(@PathVariable Integer id, @RequestParam String name) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setName(name);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/temperature-goal")
    public Terrarium updateTemperatureGoal(@PathVariable Integer id, @RequestParam Float temperatureGoal) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setTemperature_goal(temperatureGoal);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/humidity-goal")
    public Terrarium updateHumidityGoal(@PathVariable Integer id, @RequestParam Float humidityGoal) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setHumidity_goal(humidityGoal);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/max-temp")
    public Terrarium updateMaxTemp(@PathVariable Integer id, @RequestParam Float maxTemp) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setMax_temp(maxTemp);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/min-temp")
    public Terrarium updateMinTemp(@PathVariable Integer id, @RequestParam Float minTemp) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setMin_temp(minTemp);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/max-hum")
    public Terrarium updateMaxHum(@PathVariable Integer id, @RequestParam Float maxHum) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setMax_hum(maxHum);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/min-hum")
    public Terrarium updateMinHum(@PathVariable Integer id, @RequestParam Float minHum) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setMin_hum(minHum);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/water-time")
    public Terrarium updateWaterTime(@PathVariable Integer id, @RequestParam String waterTime) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setWater_time(Time.valueOf(waterTime)); // Expecting "HH:MM:SS" format
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/water-period")
    public Terrarium updateWaterPeriod(@PathVariable Integer id, @RequestParam Integer waterPeriod) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setWater_period(waterPeriod);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/current-temp-1")
    public Terrarium updateCurrentTemp1(@PathVariable Integer id, @RequestParam Float currentTemp1) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setCurrent_temp_1(currentTemp1);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/current-temp-2")
    public Terrarium updateCurrentTemp2(@PathVariable Integer id, @RequestParam Float currentTemp2) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setCurrent_temp_2(currentTemp2);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/current-hum")
    public Terrarium updateCurrentHum(@PathVariable Integer id, @RequestParam Float currentHum) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setCurrent_hum(currentHum);
        return terrariumRepository.save(terrarium);
    }

    @PutMapping("/{id}/temperature-thermostat")
    public Terrarium updateTemperatureThermostat(@PathVariable Integer id, @RequestParam Float temperatureThermostat) {
        Terrarium terrarium = terrariumRepository.findById(id).orElseThrow(() -> new RuntimeException("Terrarium not found with ID: " + id));
        terrarium.setTemperature_thermostat(temperatureThermostat);
        return terrariumRepository.save(terrarium);
    }
    
    
}
