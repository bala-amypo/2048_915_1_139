package com.example.demo.service;

import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    // Get all universities
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // Get university by ID
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: University with ID " + id + " not found"));
    }

    // Add new university
    public University addUniversity(University university) {
        if (university.getName() == null || university.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid Topic Name: Topic name cannot be empty");
        }

        if (universityRepository.existsByName(university.getName())) {
            throw new IllegalArgumentException("Duplicate Name: University already exists");
        }

        return universityRepository.save(university);
    }

    // Update existing university
    public University updateUniversity(Long id, University updated) {
        University existing = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: University with ID " + id + " not found"));

        if (updated.getAccreditationLevel() == null) {
            throw new IllegalArgumentException("Invalid Topic Name: Accreditation level is required");
        }

        existing.setName(updated.getName());
        existing.setAccreditationLevel(updated.getAccreditationLevel());
        existing.setActive(updated.getActive());

        return universityRepository.save(existing);
    }

    // Deactivate a university
    public University deactivateUniversity(Long id) {
        University existing = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: University with ID " + id + " not found"));

        if (!existing.getActive()) {
            throw new RuntimeException("Inactive Course: University is already inactive");
        }

        existing.setActive(false);
        return universityRepository.save(existing);
    }
}
