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

    private Boolean active;

    private Integer minTopicOverlap;      // Added for TransferEvaluation
    private Double allowedCreditDiff;     // Added for TransferEvaluation

    public TransferRule() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public University getSourceUniversity() { return sourceUniversity; }
    public void setSourceUniversity(University sourceUniversity) { this.sourceUniversity = sourceUniversity; }

    public University getTargetUniversity() { return targetUniversity; }
    public void setTargetUniversity(University targetUniversity) { this.targetUniversity = targetUniversity; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Integer getMinTopicOverlap() { return minTopicOverlap; }
    public void setMinTopicOverlap(Integer minTopicOverlap) { this.minTopicOverlap = minTopicOverlap; }

    public Double getAllowedCreditDiff() { return allowedCreditDiff; }
    public void setAllowedCreditDiff(Double allowedCreditDiff) { this.allowedCreditDiff = allowedCreditDiff; }
}
