package com.example.demo.service.Impl;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository resultRepository;

    public TransferEvaluationServiceImpl(TransferEvaluationResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<TransferEvaluationResult> evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        return resultRepository.findBySourceCourseIdOrTargetCourseId(sourceCourseId, targetCourseId);
    }
}
