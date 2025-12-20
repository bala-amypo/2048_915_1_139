package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;

import java.util.List;

public interface TransferEvaluationService {

    TransferEvaluationResult evaluate(Long sourceCourseId, Long targetCourseId);

    TransferEvaluationResult getEvaluationById(Long id);

    List<TransferEvaluationResult> getEvaluationsByCourse(Long courseId);
}
