package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluations")
public class TransferEvaluationController {
    
    @GetMapping
    public String getEvaluations() {
        return "Evaluations endpoint";
    }
}