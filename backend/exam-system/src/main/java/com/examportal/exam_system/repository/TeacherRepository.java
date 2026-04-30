package com.examportal.exam_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.examportal.exam_system.model.Exam;
import com.examportal.exam_system.model.Teacher;

import java.util.Optional;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

    // Used by AuthController to find teacher by email on login
    Optional<Teacher> findByEmail(String email);

    // Used by AuthController to find teacher by name on login
    Optional<Teacher> findByName(String name);
}