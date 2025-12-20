package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return repository.save(course);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Course> getAll() {
        return repository.findAll();
    }
}
