package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Universities")
@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping
    public ResponseEntity<University> createUniversity(@RequestBody University university) {
        return ResponseEntity.ok(universityService.saveUniversity(university));
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(
            @PathVariable Long id,
            @RequestBody University university) {

        university.setId(id);
        return ResponseEntity.ok(universityService.saveUniversity(university));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.getUniversityById(id));
    }

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        University uni = universityService.getUniversityById(id);
        uni.setActive(false);
        universityService.saveUniversity(uni);
        return ResponseEntity.ok("University deactivated successfully");
    }
}
