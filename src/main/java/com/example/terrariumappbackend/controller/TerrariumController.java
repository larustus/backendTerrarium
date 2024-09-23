package com.example.terrariumappbackend.controller;

import com.example.terrariumappbackend.dto.TerrariumDisplayDTO;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;
import com.example.terrariumappbackend.service.TerrariumService;
import com.example.terrariumappbackend.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/terrariums")
public class TerrariumController {
    private final TerrariumService terrariumService;
    private final UserService userService;

    @Autowired
    public TerrariumController(TerrariumService terrariumService, UserService userService){
        this.terrariumService = terrariumService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Terrarium> getAllTerrariums(){
        return terrariumService.getAllTerrariums();
    }

    @GetMapping("/{terrariumId}")
    public TerrariumDisplayDTO getTerrariumById(@PathVariable("terrariumId") Integer terrariumId){
        Optional<Terrarium> optionalTerrarium = terrariumService.findTerrariumById(terrariumId);
        if (optionalTerrarium.isPresent()){
            return terrariumService.convertToDto(optionalTerrarium.get());
        }else{
            throw new RuntimeException("Terrarium not found");
        }
    }

    @PostMapping("/addTerrarium/{userId}")
    public Terrarium createTerrarium(@RequestBody Terrarium terrarium, @PathVariable Integer userId){
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isPresent()){
            terrarium.setUser(optionalUser.get());
            return terrariumService.save(terrarium);
        }else{
            throw new RuntimeException("User not found");
        }
    }

    @PutMapping("/changeTempGoal/{terrariumId}/{tempGoal}")
    public Terrarium changeTempGoal(@PathVariable Integer terrariumId, @PathVariable Integer tempGoal){
        return terrariumService.changeTemperatureGoal(terrariumId, tempGoal);
    }

    @PutMapping("/changeHumGoal/{terrariumId}/{humGoal}")
    public Terrarium changeHumGoal(@PathVariable Integer terrariumId, @PathVariable Integer humGoal){
        return terrariumService.changeHumidityGoal(terrariumId, humGoal);
    }

    @PutMapping("/changeMaxTemp/{terrariumId}/{maxTemp}")
    public Terrarium changeMaxTemp(@PathVariable Integer terrariumId, @PathVariable Integer maxTemp){
        return terrariumService.changeMaxTemp(terrariumId, maxTemp);
    }

    @PutMapping("/changeMinTemp/{terrariumId}/{minTemp}")
    public Terrarium changeMinTemp(@PathVariable Integer terrariumId, @PathVariable Integer minTemp){
        return terrariumService.changeMinTemp(terrariumId, minTemp);
    }

    @PutMapping("/changeMaxHun/{terrariumId}/{maxHum}")
    public Terrarium changeMaxHum(@PathVariable Integer terrariumId, @PathVariable Integer maxHum){
        return terrariumService.changeMaxHum(terrariumId, maxHum);
    }

    @PutMapping("/changeMinHum/{terrariumId}/{minHum}")
    public Terrarium changeMinHum(@PathVariable Integer terrariumId, @PathVariable Integer minHum){
        return terrariumService.changeMinHum(terrariumId, minHum);
    }
}
