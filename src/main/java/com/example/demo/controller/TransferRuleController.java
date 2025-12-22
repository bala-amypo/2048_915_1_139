package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Transfer Rules")
@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRule(@RequestBody Map<String, Object> rule) {
        return ResponseEntity.ok(rule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRule(
            @PathVariable Long id,
            @RequestBody Map<String, Object> rule) {

        rule.put("id", id);
        return ResponseEntity.ok(rule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("id", id, "message", "Transfer rule details"));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRules() {
        return ResponseEntity.ok(Map.of("message", "All transfer rules list"));
    }
}
