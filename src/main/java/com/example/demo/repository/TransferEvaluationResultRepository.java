package com.example.demo.repository;

import com.example.demo.entity.TransferEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransferEvaluationRepository extends JpaRepository<TransferEvaluation, Long> {
    List<TransferEvaluation> findBySourceCourseId(Long sourceCourseId);
}
