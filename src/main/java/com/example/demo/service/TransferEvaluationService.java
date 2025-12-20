package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferEvaluationResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationService {

    private final TransferEvaluationResultRepository repository;

    public TransferEvaluationService(TransferEvaluationResultRepository repository) {
        this.repository = repository;
    }

    public TransferEvaluationResult evaluate(Long sourceCourseId, Long targetCourseId) {
        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourseId(sourceCourseId);
        result.setTargetCourseId(targetCourseId);
        result.setResult("EVALUATED");
        return repository.save(result);
    }

    public TransferEvaluationResult getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
    }

    public List<TransferEvaluationResult> getByCourse(Long courseId) {
        return repository.findBySourceCourseId(courseId);
    }
}
