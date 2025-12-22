package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Universities")
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    // CREATE
    @PostMapping
    public University createUniversity(@RequestBody University university) {
        return universityService.saveUniversity(university);
    }

    // UPDATE
    @PutMapping("/{id}")
    public University updateUniversity(
            @PathVariable Long id,
            @RequestBody University university) {
        university.setId(id);
        return universityService.saveUniversity(university);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    // GET ALL
    @GetMapping
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    // SOFT DELETE (DEACTIVATE)
    @PutMapping("/{id}/deactivate")
    public String deactivate(@PathVariable Long id) {
        University uni = universityService.getUniversityById(id);
        uni.setActive(false);
        universityService.saveUniversity(uni);
        return "deactivated";
    }
}
