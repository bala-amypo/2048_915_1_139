package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository repository;

    public TransferRuleServiceImpl(TransferRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransferRule saveRule(TransferRule rule) {
        return repository.save(rule);
    }

    @Override
    public TransferRule getActiveRule(Long sourceUniversityId, Long targetUniversityId) {
        return repository
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        sourceUniversityId, targetUniversityId)
                .orElse(null);
    }
}
