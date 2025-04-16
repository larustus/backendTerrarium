package com.example.terrariumappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.terrariumappbackend.entity.Terrarium;
import com.example.terrariumappbackend.entity.User;

import java.util.List;


public interface TerrariumRepository extends CrudRepository<Terrarium, Integer>{
    List<Terrarium> findByUser(User user);

}
