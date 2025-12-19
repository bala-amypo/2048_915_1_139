package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleService {

    @Autowired
    private TransferRuleRepository ruleRepository;

    public List<TransferRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public TransferRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: TransferRule with ID " + id + " not found"));
    }

    public TransferRule addRule(TransferRule rule) {
        if (rule.getValue() <= 0) {
            throw new IllegalArgumentException("Invalid Credit/Value: Value must be > 0");
        }
        return ruleRepository.save(rule);
    }
}
