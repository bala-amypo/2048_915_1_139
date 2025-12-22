package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Course Content Topics")
@RestController
@RequestMapping("/api/course-content-topics")
public class CourseContentTopicController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTopic(@RequestBody Map<String, Object> topic) {
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTopic(
            @PathVariable Long id,
            @RequestBody Map<String, Object> topic) {

        topic.put("id", id);
        return ResponseEntity.ok(topic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("id", id, "message", "Topic details"));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTopics() {
        return ResponseEntity.ok(Map.of("message", "All topics list"));
    }
}
