package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {
    
    @GetMapping
    public String getTopics() {
        return "Topics endpoint";
    }
}