package com.example.terrariumappbackend.repository;

import com.example.terrariumappbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
