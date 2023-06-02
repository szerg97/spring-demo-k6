package com.example.springdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.sql.Timestamp;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date departure;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date arrival;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    public TravelInfo(String name, String email, String start, String destination, Date departure, Date arrival) {
        this.name = name;
        this.email = email;
        this.start = start;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }
}
