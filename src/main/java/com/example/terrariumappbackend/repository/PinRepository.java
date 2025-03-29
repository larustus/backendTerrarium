package com.example.terrariumappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.terrariumappbackend.entity.Pin;
import com.example.terrariumappbackend.entity.User;

public interface PinRepository extends CrudRepository<Pin, Integer>{
    List<Pin> findByUser(User user);

    @Query("SELECT p FROM Pin p WHERE p.user.id = :user_id AND p.terrarium_id IS NULL AND p.function = :function")
    List<Pin> findFreePinsByUserAndFunction(@Param("user_id") Integer user_id, @Param("function") String function);

    @Query("SELECT p FROM Pin p WHERE p.user.id = :user_id AND p.terrarium_id IS NULL AND p.function LIKE :prefix%")
    List<Pin> findPinDSByUser(@Param("user_id") Integer user_id, @Param("prefix") String prefix);

}
