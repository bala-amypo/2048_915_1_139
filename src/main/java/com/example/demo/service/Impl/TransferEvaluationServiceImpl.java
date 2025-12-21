package com.example.demo.service.impl;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository repository;

    public TransferEvaluationServiceImpl(TransferEvaluationResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransferEvaluationResult evaluate(Long sourceCourseId) {
        TransferEvaluationResult result = new TransferEvaluationResult();
        return repository.save(result);
    }
}
