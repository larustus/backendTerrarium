package com.example.terrariumappbackend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.terrariumappbackend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
