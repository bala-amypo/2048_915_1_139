package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferRuleRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferRuleService {

    private final TransferRuleRepository repository;

    public TransferRuleService(TransferRuleRepository repository) {
        this.repository = repository;
    }

    public TransferRule create(TransferRule rule) {
        rule.setActive(true);
        return repository.save(rule);
    }

    public TransferRule update(Long id, TransferRule rule) {
        TransferRule existing = getById(id);
        existing.setSourceCourse(rule.getSourceCourse());
        existing.setTargetCourse(rule.getTargetCourse());
        existing.setEquivalent(rule.isEquivalent());
        return repository.save(existing);
    }

    public TransferRule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    public TransferRule getByPair(Long sourceId, Long targetId) {
        return repository
                .findBySourceCourseIdAndTargetCourseId(sourceId, targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    public TransferRule deactivate(Long id) {
        TransferRule rule = getById(id);
        rule.setActive(false);
        return repository.save(rule);
    }
}
