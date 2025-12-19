package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository repository; // REQUIRED NAME

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University univ) {
        if (repository.findByName(univ.getName()) != null) {
            throw new IllegalArgumentException("exists");
        }
        univ.setActive(true);
        return repository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University db = getUniversityById(id);
        db.setName(univ.getName());
        db.setAccreditationLevel(univ.getAccreditationLevel());
        db.setCountry(univ.getCountry());
        return repository.save(db);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University u = getUniversityById(id);
        u.setActive(false);
        repository.save(u);
    }
}
