package com.example.demo.service;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private UniversityRepository repository;

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University univ) {
        if (repository.findByName(univ.getName()) != null) {
            throw new RuntimeException("University name already exists"); // "exists"
        }
        if (univ.getActive() == null) {
            univ.setActive(true);
        }
        return repository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found")); // "not found"

        if (!existing.getName().equals(univ.getName()) &&
                repository.findByName(univ.getName()) != null) {
            throw new RuntimeException("University name already exists"); // "exists"
        }

        existing.setName(univ.getName());
        existing.setAccreditationLevel(univ.getAccreditationLevel());
        existing.setCountry(univ.getCountry());
        existing.setActive(univ.getActive());

        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found")); // "not found"
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found")); // "not found"
        existing.setActive(false);
        repository.save(existing);
    }
}
