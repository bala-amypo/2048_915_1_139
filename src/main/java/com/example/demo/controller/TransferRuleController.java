package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    private final TransferRuleService service;

    public TransferRuleController(TransferRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TransferRule create(@RequestBody TransferRule rule) {
        return service.create(rule);
    }

    @PutMapping("/{id}")
    public TransferRule update(
            @PathVariable Long id,
            @RequestBody TransferRule rule
    ) {
        return service.update(id, rule);
    }

    @GetMapping("/{id}")
    public TransferRule getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public TransferRule getByPair(
            @PathVariable Long sourceId,
            @PathVariable Long targetId
    ) {
        return service.getByPair(sourceId, targetId);
    }

    @PutMapping("/{id}/deactivate")
    public TransferRule deactivate(@PathVariable Long id) {
        return service.deactivate(id);
    }
}
