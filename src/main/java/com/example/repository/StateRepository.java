package com.example.repository;

import java.util.List;
import com.example.controller.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findAll();
    List<State> findByicao24(String icao24);
    @Query(value = "SELECT * from state WHERE origin_country=?1 ", nativeQuery = true)
    List<State> findByorigin_country(String origin_country);

}