package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
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
    public TransferRule createRule(TransferRule rule) {
        if (rule.getMinimumOverlapPercentage() < 0 || rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("Invalid overlap percentage");
        }
        return repository.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));

        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        existing.setActive(rule.getActive());

        return repository.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));

        rule.setActive(false);
        repository.save(rule);
    }
}
