package com.example.terrariumappbackend.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.terrariumappbackend.entity.Reading;
import java.util.List;
import java.util.Date;

public interface ReadingRepository extends CrudRepository<Reading, Integer>{
    Reading findFirstByTerrarium_IdOrderByDateDesc(Integer terrarium_id);

    @Query("SELECT r FROM Reading r WHERE DATE(r.date) = :date AND r.terrarium.id = :terrarium_id ORDER BY r.date ASC")
    List<Reading> findByDateAndTerrariumId(@Param("date") LocalDate date, Integer terrarium_id);
    Reading findFirstByTerrarium_Id(Integer terrarium_id);

    Reading findFirstByDate(Date date);

    @Query("SELECT r FROM Reading r WHERE DATE(r.date) = :date ORDER BY r.date ASC")
    Reading findFirstReadingOfDay(@Param("date") LocalDate date);

    void deleteByTerrarium_Id(Integer terrariumId);
}
