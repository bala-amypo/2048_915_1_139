package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course Content Topics")
@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    @PostMapping
    public String createTopic() {
        return "created";
    }

    @PutMapping("/{id}")
    public String updateTopic(@PathVariable Long id) {
        return "updated";
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id) {
        return "topic";
    }

    @GetMapping("/course/{courseId}")
    public String getByCourse(@PathVariable Long courseId) {
        return "topics by course";
    }
}
