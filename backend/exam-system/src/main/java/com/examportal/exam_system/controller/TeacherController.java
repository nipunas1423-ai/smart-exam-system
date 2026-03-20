package com.examportal.exam_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examportal.exam_system.model.Teacher;
import com.examportal.exam_system.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Teacher registerTeacher(@RequestBody Teacher teacher) {

        // Hash the password before saving — never store plain text
        String hashed = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(hashed);

        Teacher saved = teacherRepository.save(teacher);

        // Never return the hashed password in the response
        saved.setPassword(null);
        return saved;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        // Strip passwords from every teacher in the list
        teachers.forEach(t -> t.setPassword(null));
        return teachers;
    }
}