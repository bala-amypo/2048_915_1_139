package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TransferEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceCourseId;
    private Long targetCourseId;
    private int credit;

    public TransferEvaluation() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSourceCourseId() { return sourceCourseId; }
    public void setSourceCourseId(Long sourceCourseId) { this.sourceCourseId = sourceCourseId; }

    public Long getTargetCourseId() { return targetCourseId; }
    public void setTargetCourseId(Long targetCourseId) { this.targetCourseId = targetCourseId; }

    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
}
