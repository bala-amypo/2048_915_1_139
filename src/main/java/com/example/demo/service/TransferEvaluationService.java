package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;
import java.util.List;

public interface TransferEvaluationService {
    List<TransferEvaluationResult> evaluateTransfer(Long sourceCourseId, Long targetCourseId);
}
