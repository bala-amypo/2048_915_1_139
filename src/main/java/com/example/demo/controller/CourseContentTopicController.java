package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    @GetMapping("/course/{courseId}")
    public String getTopicsByCourse(@PathVariable Long courseId) {
        return "Topics for course " + courseId;
    }
}
