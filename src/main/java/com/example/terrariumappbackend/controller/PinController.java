package com.example.terrariumappbackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.DTO.PinAssignmentDTO;
import com.example.terrariumappbackend.entity.Pin;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.repository.PinRepository;
import com.example.terrariumappbackend.repository.TerrariumRepository;
import com.example.terrariumappbackend.service.PinService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pins")
public class PinController {
    private final PinService pinService;
    private final PinRepository pinRepository;
    private final TerrariumRepository terrariumRepository;

    @Autowired
    public PinController(PinService pinService, PinRepository pinRepository, TerrariumRepository terrariumRepository) {
        this.pinRepository = pinRepository;
        this.terrariumRepository = terrariumRepository;
        this.pinService = pinService;
    }
    // Lista pinów danego użytkownika
    @GetMapping("/pins/{user_id}")
    public List<Pin> getAllUserPins(@PathVariable Integer user_id) {
        return pinService.getPinsByUserId(user_id);
    }
    // Lista wolnych pinów danego użytkownika o danej funkcji
    @GetMapping("/{user_id}/{function}")
    public List<Pin> getAllFreePinsByFunction(@PathVariable Integer user_id, @PathVariable String function) {
        return pinService.getAllFreePinsForUserByFunction(user_id, function);
    }
    // Lista wolnych pinów danego użytkownika dla sondy
    @GetMapping("/DS/{user_id}/{prefix}")
    public List<Pin> getAllFreeDSPins(@PathVariable Integer user_id, @PathVariable String prefix) {
        return pinService.getAllFreeDSPinsForUser(user_id, prefix);
    }
    
    @GetMapping("assigned/{user_id}/{terrarium_id}")
    public ResponseEntity<List<Map<String, Object>>> getAllAssignedPins(@PathVariable Integer user_id, @PathVariable Integer terrarium_id) {
        List<Pin> pins = pinService.getAllPinsByTerrariumId(terrarium_id, user_id);
        List<Map<String, Object>> assignedPins = pins.stream().map(pin -> {
            Map<String, Object> pinMap = Map.of(
                    "id", pin.getId(),
                    "function", pin.getFunction()
            );
            return pinMap;
        }).toList();
        return ResponseEntity.ok(assignedPins);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> postMethodName(@RequestBody PinAssignmentDTO dto) {
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            // Retrieve the terrarium using the provided ID
            Terrarium terrarium = terrariumRepository.findById(dto.getTerrarium_id())
                    .orElseThrow(() -> new IllegalArgumentException("Terrarium not found with ID: " + dto.getTerrarium_id()));
            logger.info("Found Terrarium with ID: {}", terrarium.getId());

            // Assign Probe Pin (new assignment)
            if (dto.getProbe_pin() != null) {
                Pin probePin = pinRepository.findById(dto.getProbe_pin())
                        .orElseThrow(() -> new IllegalArgumentException("Pin not found with ID: " + dto.getProbe_pin()));
                probePin.setTerrarium_id(terrarium.getId());
                pinRepository.save(probePin);
                logger.info("Assigned Probe pin (ID: {}) to Terrarium (ID: {})", dto.getProbe_pin(), terrarium.getId());
            }

            // Assign PWM Pin (Heating)
            if (dto.getPwm_pin() != null) {
                Pin pwmPin = pinRepository.findById(dto.getPwm_pin())
                        .orElseThrow(() -> new IllegalArgumentException("Pin not found with ID: " + dto.getPwm_pin()));
                pwmPin.setTerrarium_id(terrarium.getId());
                pinRepository.save(pwmPin);
                logger.info("Assigned PWM pin (ID: {}) to Terrarium (ID: {})", dto.getPwm_pin(), terrarium.getId());
            }

            // Assign T1 Pin (Thermometer)
            if (dto.getT1_pin() != null) {
                Pin t1Pin = pinRepository.findById(dto.getT1_pin())
                        .orElseThrow(() -> new IllegalArgumentException("Pin not found with ID: " + dto.getT1_pin()));
                t1Pin.setTerrarium_id(terrarium.getId());
                pinRepository.save(t1Pin);
                logger.info("Assigned T1 pin (Thermometer, ID: {}) to Terrarium (ID: {})", dto.getT1_pin(), terrarium.getId());
            }

            // Assign T2 Pin (Hygrometer)
            if (dto.getT2_pin() != null) {
                Pin t2Pin = pinRepository.findById(dto.getT2_pin())
                        .orElseThrow(() -> new IllegalArgumentException("Pin not found with ID: " + dto.getT2_pin()));
                t2Pin.setTerrarium_id(terrarium.getId());
                pinRepository.save(t2Pin);
                logger.info("Assigned T2 pin (Hygrometer, ID: {}) to Terrarium (ID: {})", dto.getT2_pin(), terrarium.getId());
            }

            // Assign Water Pin
            if (dto.getWater_pin() != null) {
                Pin waterPin = pinRepository.findById(dto.getWater_pin())
                        .orElseThrow(() -> new IllegalArgumentException("Pin not found with ID: " + dto.getWater_pin()));
                waterPin.setTerrarium_id(terrarium.getId());
                pinRepository.save(waterPin);
                logger.info("Assigned Water pin (ID: {}) to Terrarium (ID: {})", dto.getWater_pin(), terrarium.getId());
            }

            return ResponseEntity.ok("Pins assigned successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Error assigning pins: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error assigning pins: " + e.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error during pin assignment", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error during pin assignment: " + ex.getMessage());
        }
    }
    
}
