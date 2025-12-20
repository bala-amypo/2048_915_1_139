package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import java.util.List;

public interface TransferRuleService {
    List<TransferRule> getActiveRulesByUniversities(Long sourceUniversityId, Long targetUniversityId);
}
