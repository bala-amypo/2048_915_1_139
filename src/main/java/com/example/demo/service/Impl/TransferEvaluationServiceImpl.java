package com.example.demo.service.impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferEvaluationService;
import com.example.demo.repository.TransferEvaluationResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository repo;

    public TransferEvaluationServiceImpl(TransferEvaluationResultRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        // Implement your evaluation logic
        return repo.findByCourseId(courseId);
    }
}
