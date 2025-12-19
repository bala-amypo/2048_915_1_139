package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean active;

    // Constructors
    public Course() {}
    public Course(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
