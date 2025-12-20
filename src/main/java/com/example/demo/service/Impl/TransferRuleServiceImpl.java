package com.example.demo.service.Impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository ruleRepository;

    public TransferRuleServiceImpl(TransferRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public List<TransferRule> getActiveRulesByUniversities(Long sourceUniversityId, Long targetUniversityId) {
        return ruleRepository.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceUniversityId, targetUniversityId);
    }
}
