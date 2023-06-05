package com.example.springdemo.controller.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;

public record BookingResponse(
        String travelInfoId,
        String name,
        String email,
        LocalDate departure,
        String from,
        LocalDate arrival,
        String to,
        BigDecimal totalAmount,
        Timestamp dateOfPayment
) {
}
