package com.example.terrariumappbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.terrariumappbackend.entity.Alarm;
import com.example.terrariumappbackend.repository.AlarmRepository;

@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;

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
    
    public void updateIsActive(Integer id, boolean value) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));
    
        alarm.setActive(value);
        alarmRepository.save(alarm);
    }
    
    public void updateHighestOffshoot(Integer id, Float offshoot) {
        Alarm alarm = alarmRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alarm not found"));
    
        alarm.setHighest_offshoot(offshoot);
        alarmRepository.save(alarm);
    }
}
