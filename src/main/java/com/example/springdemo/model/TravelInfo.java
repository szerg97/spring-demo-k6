package com.example.springdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TravelInfo {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String name;
    private String email;
    private String start;
    private String destination;
    private LocalDate departure;
    private LocalDate arrival;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    public TravelInfo(String name, String email, String start, String destination, LocalDate departure, LocalDate arrival) {
        this.name = name;
        this.email = email;
        this.start = start;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

    public TravelInfo(String id, String name, String email, String start, String destination, LocalDate departure, LocalDate arrival) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.start = start;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }
}
