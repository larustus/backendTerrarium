package com.example.terrariumappbackend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.terrariumappbackend.DTO.ReadingDTO;
import com.example.terrariumappbackend.DTO.ReadingHourlyDTO;
import com.example.terrariumappbackend.entity.Reading;
import com.example.terrariumappbackend.entity.Terrarium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.terrariumappbackend.repository.ReadingRepository;
import com.example.terrariumappbackend.repository.TerrariumRepository;

@Service
public class ReadingService {
    private final ReadingRepository readingRepository;
    private final TerrariumRepository terrariumRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository, TerrariumRepository terrariumRepository){
        this.readingRepository = readingRepository;
        this.terrariumRepository = terrariumRepository;
    }

    public List<Reading> findByDateAndTerrariumId(LocalDate date, Integer terrarium_id){
        return readingRepository.findByDateAndTerrariumId(date, terrarium_id);
    }

    public Reading findNewestReadingByTerrarium(Integer terrarium_id){
        return readingRepository.findFirstByTerrarium_IdOrderByDateDesc(terrarium_id);
    }

    public Reading addReading(ReadingDTO readingDTO){
        Terrarium terrarium = terrariumRepository.findById(readingDTO.getTerrarium_id())
            .orElseThrow(() -> new IllegalArgumentException("Terrarium not found"));
        Reading reading = new Reading();
        reading.setDate(readingDTO.getDate());
        reading.setTemperature_1(readingDTO.getTemperature_1());
        reading.setTemperature_2(readingDTO.getTemperature_2());
        reading.setHumidity(readingDTO.getHumidity());
        reading.setHour(readingDTO.getHour());
        reading.setTerrarium(terrarium);

        return readingRepository.save(reading);

    }

    public List<ReadingHourlyDTO> getHourlyReadingsByDateAndTerrarium(LocalDate date, Integer terrariumId){
        List<Reading> readings = findByDateAndTerrariumId(date, terrariumId);
        // Map readings by hour for quick lookup
    Map<Integer, Reading> readingMap = readings.stream()
        .collect(Collectors.toMap(Reading::getHour, Function.identity()));

    // Build complete 24-hour list
    List<ReadingHourlyDTO> hourlyReadings = new ArrayList<>();
    for (int hour = 0; hour < 24; hour++) {
        Reading reading = readingMap.get(hour);
        if (reading != null) {
            hourlyReadings.add(new ReadingHourlyDTO(
                hour,
                reading.getTemperature_1(),
                reading.getTemperature_2(),
                reading.getHumidity()
            ));
        } else {
            hourlyReadings.add(new ReadingHourlyDTO(
                hour,
                0, // or null, based on frontend needs
                0,
                0
            ));
        }
    }

    return hourlyReadings;
    } 
}
