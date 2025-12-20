package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceCourseId;
    private Long targetCourseId;
    private boolean equivalent;
    private boolean active;

    public Long getSourceCourseId() { return sourceCourseId; }
    public Long getTargetCourseId() { return targetCourseId; }
    public boolean isEquivalent() { return equivalent; }

    public void setActive(boolean active) { this.active = active; }
}
