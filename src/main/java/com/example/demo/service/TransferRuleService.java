package com.example.demo.service;

import com.example.demo.entity.TransferRule;

public interface TransferRuleService {

    TransferRule saveRule(TransferRule rule);

    TransferRule getActiveRule(Long sourceUniversityId, Long targetUniversityId);
}
