package com.example.springdemo.repository;

import com.example.springdemo.model.TravelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelInfoRepository extends JpaRepository<TravelInfo, String> {
}
