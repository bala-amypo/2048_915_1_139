package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-evaluations")
public class TransferEvaluationController {
    private final TransferEvaluationService evaluationService;

    public TransferEvaluationController(TransferEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public ResponseEntity<TransferEvaluationResult> evaluateTransfer(@PathVariable Long sourceCourseId, @PathVariable Long targetCourseId) {
        return ResponseEntity.ok(evaluationService.evaluateTransfer(sourceCourseId, targetCourseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferEvaluationResult> getEvaluation(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TransferEvaluationResult>> getEvaluationsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsForCourse(courseId));
    }
}