package com.example.terrariumappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.entity.Pin;
import com.example.terrariumappbackend.service.PinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/pins")
public class PinController {
    private final PinService pinService;

    @Autowired
    public PinController(PinService pinService){
        this.pinService = pinService;
    }

    @GetMapping("/pins/{user_id}")
    public List<Pin> getAllUserPins(@PathVariable Integer user_id) {
        return pinService.getPinsByUserId(user_id);
    }

    @GetMapping("/{user_id}/{function}")
    public List<Pin> getAllFreePinsByFunction(@PathVariable Integer user_id, @PathVariable String function) {
        return pinService.getAllFreePinsForUserByFunction(user_id, function);
    }

    @GetMapping("/DS/{user_id}/{prefix}")
    public List<Pin> getAllFreeDSPins(@PathVariable Integer user_id, @PathVariable String prefix) {
        return pinService.getAllFreeDSPinsForUser(user_id, prefix);
    }
    
    
    
}
