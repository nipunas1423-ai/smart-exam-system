package com.examportal.exam_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.examportal.exam_system.model.Exam;
import com.examportal.exam_system.model.Student;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByEmail(String email);
    Optional<Student> findByName(String name);
}