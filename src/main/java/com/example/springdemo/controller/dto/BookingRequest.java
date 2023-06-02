package com.example.springdemo.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

public record BookingRequest(
        String name,
        String email,
        Date departure,
        String start,
        Date arrival,
        String destination,
        String account,
        BigDecimal amount
) {
}
