package com.example.terrariumappbackend.controller;

import com.example.terrariumappbackend.entity.Reading;
import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.repository.ReadingRepository;
import com.example.terrariumappbackend.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingController {
    private final ReadingService readingService;
    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingController(ReadingService readingService, ReadingRepository readingRepository){
        this.readingService = readingService;
        this.readingRepository = readingRepository;
    }

    @GetMapping("/all")
    public List<Reading> getAllReadings(){
        return readingService.getAllReadings();
    }

    @GetMapping("/t/{terrarium_id}")
    public Integer getCurrentTemperature(@PathVariable Integer terrarium_id){

        Integer xd = readingService.getCurrentTemperature(terrarium_id);
        return xd;
    }

    @GetMapping("/day/{date}/terrarium/{terrarium_id}")
    public List<Reading> getReadingsByDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date , @PathVariable("terrarium_id") Integer terrarium_id){
        return readingService.findByDateAndTerrarium_Id(date, terrarium_id);
    }

    @GetMapping("/{terrarium_id}")
    public Reading getReadingByTerrariumId(@PathVariable Integer terrarium_id){
        return readingService.findByTerrariumId(terrarium_id);
    }

    @GetMapping("/r/{reading_id}")
    public Date getDateByReadingId(@PathVariable Integer reading_id){
        return readingService.findByReadingId(reading_id);
    }

    @GetMapping("/s/{date}")
    public Reading getReadingByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return readingRepository.findFirstReadingOfDay(date);
    }
}
