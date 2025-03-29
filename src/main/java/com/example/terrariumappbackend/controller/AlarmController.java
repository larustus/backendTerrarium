package com.example.terrariumappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.entity.Alarm;
import com.example.terrariumappbackend.repository.AlarmRepository;
import com.example.terrariumappbackend.service.AlarmService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/alarms")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private AlarmRepository alarmRepository;

    @GetMapping("/terrarium/{terrarium_id}")
    public List<Alarm> getAlarmsByTerrariumId(@PathVariable Integer terrarium_id) {
        return alarmService.getAlarmsByTerrariumId(terrarium_id);
    }
    
    @GetMapping("/active/{terrariumId}")
    public ResponseEntity<Alarm> getActiveAlarm(@PathVariable Integer terrariumId) {
        return alarmService.getActiveAlarm(terrariumId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    

    @PutMapping("/{id}/active")
    public ResponseEntity<String> updateIsActive(@PathVariable Integer id, @RequestParam boolean value) {
        alarmService.updateIsActive(id, value);
        return ResponseEntity.ok("Updated isActive to " + value);
    }


    @PutMapping("/{id}/offshoot")
    public ResponseEntity<String> updateHighestOffshoot(@PathVariable Integer id, @RequestParam Float offshoot) {
        alarmService.updateHighestOffshoot(id, offshoot);
        return ResponseEntity.ok("Updated isActive to " + offshoot);
    }

    @PostMapping("/add")
    public ResponseEntity<Alarm> createAlarm(@RequestBody Alarm newAlarm) {
        newAlarm.setActive(true); // force isActive = true
        Alarm saved = alarmRepository.save(newAlarm);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAlarm(@PathVariable Integer id) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));

        if (alarm.isActive()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Cannot delete an active alarm. Please deactivate it first.");
        }

        alarmRepository.deleteById(id);
        return ResponseEntity.ok("Alarm deleted successfully.");
    }

    
}
