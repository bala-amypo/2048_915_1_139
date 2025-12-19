package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int value;
    private boolean active;

    public TransferRule() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
