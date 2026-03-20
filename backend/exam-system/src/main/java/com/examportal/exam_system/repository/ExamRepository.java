package com.examportal.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.exam_system.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}