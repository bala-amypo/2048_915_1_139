package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceCourseId;
    private Long targetCourseId;
    private String result;

    public Long getId() { return id; }
    public Long getSourceCourseId() { return sourceCourseId; }
    public Long getTargetCourseId() { return targetCourseId; }
    public String getResult() { return result; }

    public void setSourceCourseId(Long sourceCourseId) {
        this.sourceCourseId = sourceCourseId;
    }

    public void setTargetCourseId(Long targetCourseId) {
        this.targetCourseId = targetCourseId;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
