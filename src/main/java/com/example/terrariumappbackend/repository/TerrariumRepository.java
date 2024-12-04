package com.example.terrariumappbackend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;

import java.util.List;


public interface TerrariumRepository extends CrudRepository<Terrarium, Integer>{
    List<Terrarium> findByUser(User user);
}
