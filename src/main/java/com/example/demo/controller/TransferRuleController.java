package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {
    
    @GetMapping
    public String getRules() {
        return "Transfer rules endpoint";
    }
}