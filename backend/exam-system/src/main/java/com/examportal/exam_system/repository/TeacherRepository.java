package com.examportal.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.exam_system.model.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Used by AuthController to find teacher by email on login
    Optional<Teacher> findByEmail(String email);

    // Used by AuthController to find teacher by name on login
    Optional<Teacher> findByName(String name);
}