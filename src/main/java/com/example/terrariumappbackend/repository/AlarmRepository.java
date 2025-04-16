package com.example.terrariumappbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.terrariumappbackend.entity.Alarm;

public interface AlarmRepository extends CrudRepository<Alarm, Integer>{
    
    @Query("SELECT a from Alarm a WHERE a.terrarium.id = :terrarium_id")
    List<Alarm> findAlarmsByTerrariumId(@Param("terrarium_id") Integer terrarium_id);

    Optional<Alarm> findFirstByTerrarium_IdAndIsActiveTrue(Integer terrariumId);
    Optional<Alarm> findFirstByTerrarium_IdAndIsActiveTrueAndType(Integer terrariumId, String type);

    void deleteByTerrarium_Id(Integer terrariumId);
}
