package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transfer Rules")
@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    @PostMapping
    public String createRule() {
        return "created";
    }

    @PutMapping("/{id}")
    public String updateRule(@PathVariable Long id) {
        return "updated";
    }

    @GetMapping("/{id}")
    public String getRule(@PathVariable Long id) {
        return "rule";
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public String getRules(
            @PathVariable Long sourceId,
            @PathVariable Long targetId) {
        return "rules";
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        return "deactivated";
    }
}
