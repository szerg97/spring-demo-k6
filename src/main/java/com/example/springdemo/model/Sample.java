package com.example.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sample{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "value")
    private int value;
    @Column(name = "currency",length = 3)
    private String currency;
}
