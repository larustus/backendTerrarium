package com.example.terrariumappbackend.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terrariumappbackend.DTO.AddAlarmDTO;
import com.example.terrariumappbackend.entity.Alarm;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.repository.AlarmRepository;
import com.example.terrariumappbackend.repository.TerrariumRepository;
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
    @Autowired
    private TerrariumRepository terrariumRepository;

    @GetMapping("/terrarium/{terrarium_id}")
    public List<Alarm> getAlarmsByTerrariumId(@PathVariable Integer terrarium_id) {
        return alarmService.getAlarmsByTerrariumId(terrarium_id);
    }
    // najnowszy alarm wlaczony
    @GetMapping("/active/{terrariumId}")
    public ResponseEntity<Alarm> getActiveAlarm(@PathVariable Integer terrariumId) {
        return alarmService.getActiveAlarm(terrariumId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    // najnowszy alarm wlaczony o danym typie
    @GetMapping("/active/{terrariumId}/{type}")
    public ResponseEntity<Alarm> getActiveAlarmByType(@PathVariable Integer terrariumId, @PathVariable String type) {
        return alarmService.getActiveAlarmByType(terrariumId, type)
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

    @PutMapping("/{id}/end")
    public ResponseEntity<String> updateEndTime(@PathVariable Integer id, @RequestParam Time endTime) {
        alarmService.updateEndTime(id, endTime);
        return ResponseEntity.ok("Updated end time to " + endTime);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createAlarm(@RequestBody AddAlarmDTO alarmData) {
        try {
            Terrarium terrarium = terrariumRepository.findById(alarmData.getTerrarium_id())
                .orElseThrow(() -> new RuntimeException("Terrarium not found"));
            Alarm alarm = new Alarm();
            alarm.setStart_time(alarmData.getStart_time());
            alarm.setType(alarmData.getType());
            alarm.setTerrarium(terrarium);
            alarm.setHighest_offshoot(alarmData.getHighest_offshoot());
            alarm.setActive(true);
            Alarm savedAlarm = alarmRepository.save(alarm);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Alarm created successfully with ID: " + savedAlarm.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error creating alarm: " + e.getMessage());
        }
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
