package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-evaluation")
public class TransferEvaluationController {

    @PostMapping
    public String evaluate() {
        return "Evaluation done";
    }
}
