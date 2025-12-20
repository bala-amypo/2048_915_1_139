package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {
        University existing = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));

        existing.setName(university.getName());
        existing.setCountry(university.getCountry());
        existing.setAccreditationLevel(university.getAccreditationLevel());
        existing.setActive(university.getActive());

        return universityRepository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
