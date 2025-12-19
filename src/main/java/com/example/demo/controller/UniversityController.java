package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @GetMapping
    public String getAll() {
        return "Get all universities";
    }

    @PostMapping
    public String create() {
        return "Create university";
    }
}
