package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private University sourceUniversity;

    @ManyToOne
    private University targetUniversity;

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private Boolean active = true;

    
}
