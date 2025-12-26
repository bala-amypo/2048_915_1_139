package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    
    @Autowired
    private UniversityService universityService;
    
    @PostMapping
    public University create(@RequestBody University university) {
        return universityService.createUniversity(university);
    }
    
    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }
    
    @PutMapping("/{id}")
    public University update(@PathVariable Long id, @RequestBody University university) {
        return universityService.updateUniversity(id, university);
    }
    
    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        universityService.deactivateUniversity(id);
    }
}