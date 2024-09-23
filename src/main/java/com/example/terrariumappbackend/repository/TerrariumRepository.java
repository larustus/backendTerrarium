package com.example.terrariumappbackend.repository;

import com.example.terrariumappbackend.entity.Reading;
import com.example.terrariumappbackend.entity.Terrarium;
import org.springframework.data.repository.CrudRepository;

public interface TerrariumRepository extends CrudRepository<Terrarium, Integer> {

}
