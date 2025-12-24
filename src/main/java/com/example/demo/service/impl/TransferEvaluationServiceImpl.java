package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        Course sourceCourse = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course targetCourse = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));
        
        if (!sourceCourse.isActive() || !targetCourse.isActive()) {
            throw new IllegalArgumentException("Both courses must be active");
        }

        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(targetCourseId);
        
        double totalSourceWeight = sourceTopics.stream().mapToDouble(CourseContentTopic::getWeightPercentage).sum();
        if (totalSourceWeight == 0) totalSourceWeight = 100.0;
        
        double overlapWeight = 0.0;
        for (CourseContentTopic sourceTopic : sourceTopics) {
            for (CourseContentTopic targetTopic : targetTopics) {
                if (sourceTopic.getTopicName().equalsIgnoreCase(targetTopic.getTopicName())) {
                    overlapWeight += Math.min(sourceTopic.getWeightPercentage(), targetTopic.getWeightPercentage());
                }
            }
        }
        
        double overlapPercentage = (overlapWeight / totalSourceWeight) * 100.0;
        
        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                sourceCourse.getUniversity().getId(), targetCourse.getUniversity().getId());
        
        boolean eligible = false;
        String notes = "No active transfer rule found between universities";
        
        for (TransferRule rule : rules) {
            int creditDiff = Math.abs(sourceCourse.getCreditHours() - targetCourse.getCreditHours());
            boolean creditOk = rule.getCreditHourTolerance() == null || creditDiff <= rule.getCreditHourTolerance();
            boolean overlapOk = overlapPercentage >= rule.getMinimumOverlapPercentage();
            
            if (creditOk && overlapOk) {
                eligible = true;
                notes = "Transfer approved based on rule " + rule.getId();
                break;
            }
        }
        
        if (!rules.isEmpty() && !eligible) {
            notes = "No active rule satisfied the requirements";
        }
        
        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(sourceCourse);
        result.setTargetCourse(targetCourse);
        result.setOverlapPercentage(overlapPercentage);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(notes);
        
        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}