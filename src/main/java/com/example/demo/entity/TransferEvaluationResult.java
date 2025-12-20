package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course sourceCourse;

    @ManyToOne
    private Course targetCourse;

    private boolean isEligibleForTransfer;
    private String notes;
    private double overlapPercentage;
    private int creditHourDifference;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }

    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }

    public boolean isEligibleForTransfer() { return isEligibleForTransfer; }
    public void setIsEligibleForTransfer(boolean eligible) { this.isEligibleForTransfer = eligible; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public double getOverlapPercentage() { return overlapPercentage; }
    public void setOverlapPercentage(double overlapPercentage) { this.overlapPercentage = overlapPercentage; }

    public int getCreditHourDifference() { return creditHourDifference; }
    public void setCreditHourDifference(int creditHourDifference) { this.creditHourDifference = creditHourDifference; }
}
