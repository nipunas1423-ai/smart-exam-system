package com.examportal.exam_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.examportal.exam_system.dto.StudentDTO;
import com.examportal.exam_system.exception.ResourceNotFoundException;
import com.examportal.exam_system.model.Student;
import com.examportal.exam_system.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student registerStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());

        // Hash password before saving
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isBlank()) {
            student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existing.setName(studentDTO.getName());
        existing.setEmail(studentDTO.getEmail());
        existing.setAge(studentDTO.getAge());

        // Only update password if a new one is provided
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(existing);
    }

    public Student findByEmailOrName(String identifier) {
        return studentRepository.findByEmail(identifier)
                .or(() -> studentRepository.findByName(identifier))
                .orElse(null);
    }
}