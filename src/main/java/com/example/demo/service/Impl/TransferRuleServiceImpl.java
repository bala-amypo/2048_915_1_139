package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.entity.University;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository repo;
    private final UniversityRepository univRepo;

    public TransferRuleServiceImpl(TransferRuleRepository repo, UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        University source = univRepo.findById(rule.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Source university not found"));
        University target = univRepo.findById(rule.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Target university not found"));

        rule.setSourceUniversity(source);
        rule.setTargetUniversity(target);
        if (rule.getActive() == null) rule.setActive(true);

        return repo.save(rule);
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long ruleId) {
        TransferRule rule = repo.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}
