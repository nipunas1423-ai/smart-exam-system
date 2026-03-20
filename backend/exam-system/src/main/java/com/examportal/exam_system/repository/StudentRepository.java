package com.examportal.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.exam_system.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
    Optional<Student> findByName(String name);
}