package com.example.springdemo.controller.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public record BookingResponse(
        String travelInfoId,
        String name,
        String email,
        Date departure,
        String from,
        Date arrival,
        String to,
        BigDecimal totalAmount,
        Timestamp dateOfPayment
) {
}
