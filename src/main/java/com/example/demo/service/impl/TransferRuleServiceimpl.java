package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {
    private TransferRuleRepository repo;
    private UniversityRepository univRepo;

    @Override
    public TransferRule createRule(TransferRule rule) {
        if (rule.getMinimumOverlapPercentage() < 0 || rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("Overlap percentage must be between 0-100");
        }
        if (rule.getCreditHourTolerance() != null && rule.getCreditHourTolerance() < 0) {
            throw new IllegalArgumentException("Credit hour tolerance must be >= 0");
        }
        if (rule.getSourceUniversity() != null && !univRepo.findById(rule.getSourceUniversity().getId()).isPresent()) {
            throw new RuntimeException("Source university not found");
        }
        if (rule.getTargetUniversity() != null && !univRepo.findById(rule.getTargetUniversity().getId()).isPresent()) {
            throw new RuntimeException("Target university not found");
        }
        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}