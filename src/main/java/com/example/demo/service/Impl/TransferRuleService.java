package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.entity.University;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository repo;   // REQUIRED
    private final UniversityRepository univRepo; // REQUIRED

    public TransferRuleServiceImpl(TransferRuleRepository repo,
                                   UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {

        University source = univRepo.findById(
                rule.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        University target = univRepo.findById(
                rule.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        rule.setSourceUniversity(source);
        rule.setTargetUniversity(target);
        rule.setActive(true);

        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule db = getRuleById(id);
        db.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        db.setCreditHourTolerance(rule.getCreditHourTolerance());
        return repo.save(db);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public TransferRule getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule r = getRuleById(id);
        r.setActive(false);
        repo.save(r);
    }
}
