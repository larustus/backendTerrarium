package com.example.terrariumappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.service.TerrariumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.terrariumappbackend.DTO.TerrariumDisplayDTO;

@RestController
@RequestMapping("/terrariums")
public class TerrariumController {
    private final TerrariumService terrariumService;

    @Autowired
    public TerrariumController(TerrariumService terrariumService){
        this.terrariumService = terrariumService;
    }

    @GetMapping("/user/{user_id}")
    public List<TerrariumDisplayDTO> getTerrarriumsByUserId(@PathVariable Integer user_id) {
        return terrariumService.getTerrarriumsByUserId(user_id);
    }
    
    
}
