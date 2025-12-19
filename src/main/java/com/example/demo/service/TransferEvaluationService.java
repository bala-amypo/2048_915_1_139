package com.example.demo.service;

import com.example.demo.entity.TransferEvaluation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferEvaluationService {

    @Autowired
    private TransferEvaluationRepository evaluationRepository;

    public List<TransferEvaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public TransferEvaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: TransferEvaluation with ID " + id + " not found"));
    }

    public TransferEvaluation addEvaluation(TransferEvaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
}
