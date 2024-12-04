package com.example.terrariumappbackend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.terrariumappbackend.entity.Reading;
import java.util.List;
import com.example.terrariumappbackend.repository.ReadingRepository;

@Service
public class ReadingService {
    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository){
        this.readingRepository = readingRepository;
    }

    public List<Reading> findByDateAndTerrariumId(LocalDate date, Integer terrarium_id){
        return readingRepository.findByDateAndTerrariumId(date, terrarium_id);
    }

    public Reading findNewestReadingByTerrarium(Integer terrarium_id){
        return readingRepository.findFirstByTerrarium_IdOrderByDateDesc(terrarium_id);
    }
    
}
