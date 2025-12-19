package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TransferEvaluationResult;
import java.util.List;

public interface TransferEvaluationResultRepository
        extends JpaRepository<TransferEvaluationResult, Long> {

    List<TransferEvaluationResult> findBySourceCourseId(Long courseId);
}
