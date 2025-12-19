package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository resultRepo;
    private final CourseRepository courseRepo;
    private final CourseContentTopicRepository topicRepo;
    private final TransferRuleRepository ruleRepo;

    public TransferEvaluationServiceImpl(TransferEvaluationResultRepository resultRepo,
                                         CourseRepository courseRepo,
                                         CourseContentTopicRepository topicRepo,
                                         TransferRuleRepository ruleRepo) {
        this.resultRepo = resultRepo;
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"
        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"

        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                source.getUniversity().getId(), target.getUniversity().getId()
        );

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);

        if (rules.isEmpty()) {
            result.setEligible(false);
            result.setNotes("No active transfer rule"); // "No active transfer rule"
            return resultRepo.save(result);
        }

        // Example simple evaluation (topic overlap check)
        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(source.getId());
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(target.getId());

        double overlap = 0;
        for (CourseContentTopic s : sourceTopics) {
            for (CourseContentTopic t : targetTopics) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    overlap += s.getWeight();
                }
            }
        }

        double totalWeight = sourceTopics.stream().mapToDouble(CourseContentTopic::getWeight).sum();
        double percentOverlap = totalWeight > 0 ? (overlap / totalWeight) * 100 : 0;

        TransferRule rule = rules.get(0); // take first active rule for simplicity
        if (percentOverlap >= rule.getMinTopicOverlap() &&
            Math.abs(source.getCreditHours() - target.getCreditHours()) <= rule.getAllowedCreditDiff()) {
            result.setEligible(true);
            result.setNotes("Eligible for transfer");
        } else {
            result.setEligible(false);
            result.setNotes("No active rule satisfied"); // "No active rule satisfied"
        }

        return resultRepo.save(result);
    }
}
