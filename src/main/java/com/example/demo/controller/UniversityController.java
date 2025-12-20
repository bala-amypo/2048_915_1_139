package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityRepository repository;

    public UniversityController(UniversityRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public University create(@RequestBody University university) {
        return repository.save(university);
    }

    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<University> getAll() {
        return repository.findAll();
    }
}
