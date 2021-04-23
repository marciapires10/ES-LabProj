package com.example.repository;

import java.util.List;
import com.example.controller.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<State, Long> {

    List<Coordinates> findAll();

}