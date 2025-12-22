package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Courses")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCourse(@RequestBody Map<String, Object> course) {
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(
            @PathVariable Long id,
            @RequestBody Map<String, Object> course) {

        course.put("id", id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("id", id, "message", "Course details"));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        return ResponseEntity.ok(Map.of("message", "All courses list"));
    }
}
