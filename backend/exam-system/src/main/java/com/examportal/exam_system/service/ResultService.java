package com.examportal.exam_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.examportal.exam_system.model.Result;
import com.examportal.exam_system.repository.ResultRepository;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public List<Result> getResultsByStudent(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    public List<Result> getResultsByExam(Long examId) {
        return resultRepository.findByExamId(examId);
    }
}