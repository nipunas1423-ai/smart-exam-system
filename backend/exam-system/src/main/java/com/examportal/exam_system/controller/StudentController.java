package com.examportal.exam_system.controller;

import com.examportal.exam_system.dto.StudentDTO;
import com.examportal.exam_system.model.Student;
import com.examportal.exam_system.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ REGISTER STUDENT
    @PostMapping("/register")
    public Student registerStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.registerStudent(studentDTO);
    }

    // ✅ GET ALL STUDENTS
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ GET STUDENT BY ID (SAFE VERSION)
    @GetMapping("/id/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    // ✅ ADD STUDENT
    @PostMapping("/add")
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // ✅ UPDATE STUDENT
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable String id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    // ✅ DELETE STUDENT
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
}
