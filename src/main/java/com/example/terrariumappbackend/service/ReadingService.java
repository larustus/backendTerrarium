package com.example.terrariumappbackend.service;

import com.example.terrariumappbackend.entity.Reading;
import com.example.terrariumappbackend.repository.ReadingRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ReadingService {
    private final ReadingRepository readingRepository;
    private static final Logger logger = Logger.getLogger(ReadingService.class.getName());

    @Autowired
    public ReadingService(ReadingRepository readingRepository){
        this.readingRepository = readingRepository;
    }

    public List<Reading> getAllReadings(){
        return (List<Reading>) readingRepository.findAll();
    }

    public Integer getCurrentTemperature(Integer terrarium_id){
        return readingRepository.findFirstByTerrarium_IdOrderByDateDesc(terrarium_id).getTemperature();
    }

    public Reading save(Reading reading){
        return readingRepository.save(reading);
    }

    public List<Reading> findByDateAndTerrarium_Id(LocalDate date, Integer terrarium_id){
        return readingRepository.findByDateAndTerrariumId(date, terrarium_id);
    }

    public Reading findByTerrariumId(Integer terrarium_id){
        return readingRepository.findFirstByTerrarium_Id(terrarium_id);
    }

    public Date findByReadingId(Integer reading_id){
        Reading r = readingRepository.findById(reading_id).orElseThrow();
        return r.getDate();
    }

    public Reading findByDate(Date date){
        return readingRepository.findFirstByDate(date);
    }
}
