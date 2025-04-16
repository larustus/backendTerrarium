package com.example.terrariumappbackend.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.entity.Alarm;
import com.example.terrariumappbackend.repository.AlarmRepository;
//import org.springframework.web.client.RestTemplate;

@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    //private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    public AlarmService(AlarmRepository alarmRepository){
        this.alarmRepository = alarmRepository;
    }

    public List<Alarm> getAlarmsByTerrariumId(Integer terrarium_id){
        return alarmRepository.findAlarmsByTerrariumId(terrarium_id);
    }

    public Optional<Alarm> getActiveAlarm(Integer terrariumId) {
        return alarmRepository.findFirstByTerrarium_IdAndIsActiveTrue(terrariumId);
    }
    
    public Optional<Alarm> getActiveAlarmByType(Integer terrariumId, String type) {
        return alarmRepository.findFirstByTerrarium_IdAndIsActiveTrueAndType(terrariumId, type);
    }


    public void updateIsActive(Integer id, boolean value) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));
    
        alarm.setActive(value);
        alarmRepository.save(alarm);
        //notifyAlarmUpdate(alarm.getTerrarium().getId());
    }
    
    public void updateHighestOffshoot(Integer id, Float offshoot) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));
    
        alarm.setHighest_offshoot(offshoot);
        alarmRepository.save(alarm);
        //notifyAlarmUpdate(alarm.getTerrarium().getId());
    }

    public void updateEndTime(Integer id, Time endTime) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));
    
        alarm.setEnd_time(endTime);
        alarmRepository.save(alarm);
        //notifyAlarmUpdate(alarm.getTerrarium().getId());
    }

    // private void notifyAlarmUpdate(Integer terrariumId) {
    //     String url = "http://localhost:3000/notify-alarms/" + terrariumId;
    //     try {
    //         restTemplate.postForLocation(url, null);
    //     } catch (Exception e) {
    //         System.err.println("Failed to notify WebSocket server: " + e.getMessage());
    //     }
    // }
}
