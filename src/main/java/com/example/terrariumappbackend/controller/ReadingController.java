package com.example.terrariumappbackend.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.terrariumappbackend.DTO.ReadingDTO;
import com.example.terrariumappbackend.DTO.ReadingHourlyDTO;
import com.example.terrariumappbackend.entity.Reading;
import com.example.terrariumappbackend.service.ReadingService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/readings")
public class ReadingController {
    private final ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService){
        this.readingService = readingService;
    }

    @GetMapping("/day/{date}/terrarium/{terrarium_id}")
    public List<ReadingHourlyDTO> getReadingsByDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date , @PathVariable("terrarium_id") Integer terrarium_id){
        return readingService.getHourlyReadingsByDateAndTerrarium(date, terrarium_id);
    }

    @GetMapping("/current/reading/{terrarium_id}")
    public Reading getNewestReadingByTerrarium(@PathVariable Integer terrarium_id) {
        return readingService.findNewestReadingByTerrarium(terrarium_id);
    }

    @PostMapping
    public ResponseEntity<Reading> saveReading(@RequestBody ReadingDTO readingDTO) {
        Reading reading = readingService.addReading(readingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reading);
    }
    
    
}
