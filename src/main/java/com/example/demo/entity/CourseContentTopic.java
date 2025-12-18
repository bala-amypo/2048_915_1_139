package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CourseContentTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;

    private String topicName;
    private Double weightPercentage;

    
}
