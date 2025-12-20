package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class TransferEvaluationController {

    private final TransferEvaluationService service;

    public TransferEvaluationController(TransferEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/evaluate")
    public TransferEvaluationResult evaluate(@RequestParam Long sourceCourseId, @RequestParam Long targetCourseId) {
        return service.evaluate(sourceCourseId, targetCourseId);
    }

    @GetMapping("/{id}")
    public TransferEvaluationResult getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<TransferEvaluationResult> getByCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }
}
