package com.examportal.exam_system.controller;

import com.examportal.exam_system.model.Student;
import com.examportal.exam_system.model.Teacher;
import com.examportal.exam_system.repository.TeacherRepository;
import com.examportal.exam_system.service.StudentService;
import com.examportal.exam_system.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private StudentService studentService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");
        String role     = request.get("role");

        if (username == null || username.isBlank())
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));

        if (password == null || password.isBlank())
            return ResponseEntity.badRequest().body(Map.of("error", "Password is required"));

        // ── ADMIN LOGIN ──
        if ("admin".equalsIgnoreCase(role)) {

            Optional<Teacher> teacherOpt = teacherRepository.findByEmail(username);
            if (teacherOpt.isEmpty()) teacherOpt = teacherRepository.findByName(username);

            if (teacherOpt.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Admin account not found"));

            Teacher teacher = teacherOpt.get();

            if (!passwordEncoder.matches(password, teacher.getPassword()))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Incorrect password"));

            String token = jwtUtil.generateToken(teacher.getEmail());
            return ResponseEntity.ok(Map.of(
                    "token",     token,
                    "role",      "admin",
                    "teacherId", teacher.getId().toString(),
                    "adminName", teacher.getName()
            ));
        }

        // ── STUDENT LOGIN ──
        Student student = studentService.findByEmailOrName(username);

        if (student == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Student account not found"));

        if (student.getPassword() == null || !passwordEncoder.matches(password, student.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Incorrect password"));

        String token = jwtUtil.generateToken(student.getEmail());
        return ResponseEntity.ok(Map.of(
                "token",       token,
                "role",        "student",
                "studentId",   student.getId().toString(),
                "studentName", student.getName(),
                "studentEmail",student.getEmail()
        ));
    }
}