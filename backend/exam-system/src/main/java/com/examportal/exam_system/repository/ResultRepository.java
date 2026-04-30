package com.examportal.exam_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.examportal.exam_system.model.Exam;
import java.util.List;
import java.util.Optional;
import com.examportal.exam_system.model.Result;

public interface ResultRepository extends MongoRepository<Result, String> {
    List<Result> findByStudentId(String studentId);
    List<Result> findByExamId(String examId);
    Optional<Result> findByStudentIdAndExamId(String studentId, String examId);
    Optional<Result> findByStudentIdAndExamId(Long studentId, Long examId);
}