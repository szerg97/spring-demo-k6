package com.example.springdemo.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingRequest(
        String name,
        String email,
        LocalDate departure,
        String start,
        LocalDate arrival,
        String destination,
        String account,
        BigDecimal amount
) {
}
