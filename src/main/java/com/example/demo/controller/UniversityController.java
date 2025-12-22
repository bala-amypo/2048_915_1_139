package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Universities")
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @PostMapping
    public String createUniversity() {
        return "created";
    }

    @PutMapping("/{id}")
    public String updateUniversity(@PathVariable Long id) {
        return "updated";
    }

    @GetMapping("/{id}")
    public String getUniversity(@PathVariable Long id) {
        return "university";
    }

    @GetMapping
    public String getAllUniversities() {
        return "all universities";
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        return "deactivated";
    }
}
