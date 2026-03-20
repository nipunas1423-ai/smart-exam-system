package com.examportal.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import com.examportal.exam_system.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentId(Long studentId);
    List<Result> findByExamId(Long examId);
    Optional<Result> findByStudentIdAndExamId(Long studentId, Long examId);
}