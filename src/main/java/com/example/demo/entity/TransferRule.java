package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course sourceCourse;

    @ManyToOne
    private Course targetCourse;

    private double minimumOverlapPercentage;
    private int creditHourTolerance;
    private boolean active;

    @ManyToOne
    private University sourceUniversity;

    @ManyToOne
    private University targetUniversity;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }

    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }

    public double getMinimumOverlapPercentage() { return minimumOverlapPercentage; }
    public void setMinimumOverlapPercentage(double minimumOverlapPercentage) { this.minimumOverlapPercentage = minimumOverlapPercentage; }

    public int getCreditHourTolerance() { return creditHourTolerance; }
    public void setCreditHourTolerance(int creditHourTolerance) { this.creditHourTolerance = creditHourTolerance; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public University getSourceUniversity() { return sourceUniversity; }
    public void setSourceUniversity(University sourceUniversity) { this.sourceUniversity = sourceUniversity; }

    public University getTargetUniversity() { return targetUniversity; }
    public void setTargetUniversity(University targetUniversity) { this.targetUniversity = targetUniversity; }
}
