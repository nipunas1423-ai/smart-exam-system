package com.examportal.exam_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.examportal.exam_system.model.Exam;

public interface ExamRepository extends MongoRepository<Exam, String> {

}