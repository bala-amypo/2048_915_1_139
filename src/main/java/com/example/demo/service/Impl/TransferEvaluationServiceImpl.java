package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepository;
    private final CourseContentTopicRepository topicRepository;
    private final TransferRuleRepository ruleRepository;
    private final TransferEvaluationResultRepository resultRepository;

    public TransferEvaluationServiceImpl(
            CourseRepository courseRepository,
            CourseContentTopicRepository topicRepository,
            TransferRuleRepository ruleRepository,
            TransferEvaluationResultRepository resultRepository
    ) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public TransferEvaluationResult evaluate(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepository.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Source course not found"));

        Course target = courseRepository.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Target course not found"));

        TransferRule rule = ruleRepository
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                )
                .orElse(null);

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);

        if (rule == null) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active transfer rule");
            return resultRepository.save(result);
        }

        double overlap = Math.min(100.0, rule.getMinimumOverlapPercentage());
        int creditDiff = Math.abs(source.getCreditHours() - target.getCreditHours());

        boolean eligible =
                overlap >= rule.getMinimumOverlapPercentage()
                        && creditDiff <= rule.getCreditHourTolerance();

        result.setOverlapPercentage(overlap);
        result.setCreditHourDifference(creditDiff);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(eligible ? "Eligible for transfer" : "Not eligible for transfer");

        return resultRepository.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsByCourse(Long courseId) {
        return resultRepository.findBySourceCourseIdOrTargetCourseId(courseId, courseId);
    }
}
