package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Courses")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @PostMapping
    public String createCourse() {
        return "created";
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable Long id) {
        return "updated";
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable Long id) {
        return "course";
    }

    @GetMapping("/university/{universityId}")
    public String getByUniversity(@PathVariable Long universityId) {
        return "courses by university";
    }

    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        return "deactivated";
    }
}
