package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository resultRepo; // REQUIRED
    private final CourseRepository courseRepo;                   // REQUIRED
    private final CourseContentTopicRepository topicRepo;        // REQUIRED
    private final TransferRuleRepository ruleRepo;               // REQUIRED

    public TransferEvaluationServiceImpl(
            TransferEvaluationResultRepository resultRepo,
            CourseRepository courseRepo,
            CourseContentTopicRepository topicRepo,
            TransferRuleRepository ruleRepo) {

        this.resultRepo = resultRepo;
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId,
                                                     Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);

        TransferRule rule =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId());

        if (rule == null) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active transfer rule");
            return resultRepo.save(result);
        }

        int diff = Math.abs(source.getCreditHours() - target.getCreditHours());
        result.setCreditHourDifference(diff);

        if (diff > rule.getCreditHourTolerance()) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active rule satisfied");
            return resultRepo.save(result);
        }

        result.setIsEligibleForTransfer(true);
        result.setNotes("Transfer eligible");
        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
