package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluation;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer-evaluations")
public class TransferEvaluationController {

    @Autowired
    private TransferEvaluationService evaluationService;

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public TransferEvaluation evaluate(
            @PathVariable Long sourceCourseId,
            @PathVariable Long targetCourseId) {
        return evaluationService.evaluateTransfer(sourceCourseId, targetCourseId);
    }

    @GetMapping("/{id}")
    public TransferEvaluation getById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<TransferEvaluation> getByCourse(@PathVariable Long courseId) {
        return evaluationService.getEvaluationsByCourse(courseId);
    }
}
