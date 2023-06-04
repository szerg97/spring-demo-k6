package com.example.springdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String travelInfoId;
    private String account;
    private BigDecimal totalAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    public PaymentInfo(String travelInfoId, String account, BigDecimal totalAmount) {
        this.travelInfoId = travelInfoId;
        this.account = account;
        this.totalAmount = totalAmount;
    }
}
