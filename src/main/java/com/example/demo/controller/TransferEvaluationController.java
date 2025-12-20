package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transfer Evaluations")
@RestController
@RequestMapping("/api/transfer-evaluations")
public class TransferEvaluationController {

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public String evaluate(
            @PathVariable Long sourceCourseId,
            @PathVariable Long targetCourseId) {
        return "evaluated";
    }

    @GetMapping("/{id}")
    public String getEvaluation(@PathVariable Long id) {
        return "evaluation";
    }

    @GetMapping("/course/{courseId}")
    public String getByCourse(@PathVariable Long courseId) {
        return "evaluations";
    }
}
