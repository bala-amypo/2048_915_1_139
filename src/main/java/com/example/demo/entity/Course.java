package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"university_id", "courseCode"})
})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private University university;

    private String courseCode;
    private String courseName;
    private Integer creditHours;
    private String description;
    private String department;
    private Boolean active = true;

    
}
