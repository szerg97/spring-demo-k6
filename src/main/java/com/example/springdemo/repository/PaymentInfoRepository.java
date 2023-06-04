package com.example.springdemo.repository;

import com.example.springdemo.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {
    Optional<PaymentInfo> findByTravelInfoId(String travelInfoId);
}
