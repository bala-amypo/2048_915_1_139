package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {
    private final TransferRuleService ruleService;

    public TransferRuleController(TransferRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<TransferRule> createRule(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @GetMapping("/universities/{sourceId}/{targetId}")
    public ResponseEntity<List<TransferRule>> getRulesForUniversities(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return ResponseEntity.ok(ruleService.getRulesForUniversities(sourceId, targetId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferRule> updateRule(@PathVariable Long id, @RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        ruleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}