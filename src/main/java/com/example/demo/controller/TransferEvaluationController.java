package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Transfer Evaluation")
@RestController
@RequestMapping("/api/transfer-evaluation")
public class TransferEvaluationController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> evaluateTransfer(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEvaluation(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("id", id, "message", "Evaluation result"));
    }
}
