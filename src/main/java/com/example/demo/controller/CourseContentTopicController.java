package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    private final CourseContentTopicService service;

    public CourseContentTopicController(CourseContentTopicService service) {
        this.service = service;
    }

    @PostMapping
    public CourseContentTopic create(@RequestBody CourseContentTopic topic) {
        return service.create(topic);
    }

    @PutMapping("/{id}")
    public CourseContentTopic update(
            @PathVariable Long id,
            @RequestBody CourseContentTopic topic
    ) {
        return service.update(id, topic);
    }

    @GetMapping("/{id}")
    public CourseContentTopic getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseContentTopic> getByCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }
}
