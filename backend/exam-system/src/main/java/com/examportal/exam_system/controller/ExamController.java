package com.examportal.exam_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examportal.exam_system.model.Exam;
import com.examportal.exam_system.model.ExamSubmission;
import com.examportal.exam_system.model.Question;
import com.examportal.exam_system.model.Result;
import com.examportal.exam_system.repository.ExamRepository;
import com.examportal.exam_system.repository.QuestionRepository;
import com.examportal.exam_system.repository.ResultRepository;
import com.examportal.exam_system.exception.ResourceNotFoundException;

import java.util.*;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired private ExamRepository examRepository;
    @Autowired private QuestionRepository questionRepository;
    @Autowired private ResultRepository resultRepository;

    @PostMapping("/create")
    public Exam createExam(@RequestBody Exam exam) {
        return examRepository.save(exam);
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable String id) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found with id: " + id));

        examRepository.delete(exam);
        return "Exam deleted successfully";
    }

    @PostMapping("/attempt")
    public ResponseEntity<?> attemptExam(@RequestBody ExamSubmission submission) {

        // ✅ check duplicate attempt
        Optional<Result> existing =
                resultRepository.findByStudentIdAndExamId(
                        submission.getStudentId(),
                        submission.getExamId()
                );

        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "error", "You have already attempted this exam",
                            "score", existing.get().getScore()
                    ));
        }

        // ✅ FIX: STORE QUESTIONS PROPERLY
        List<Question> questions =
                questionRepository.findByExamId(String.valueOf(submission.getExamId()));

        int score = 0;
        Map<String, String> correctAnswers = new HashMap<>();

        for (Question q : questions) {

            correctAnswers.put(q.getId(), q.getCorrectAnswer());

            String studentAnswer =
                    submission.getAnswers().get(q.getId());

            if (studentAnswer != null &&
                studentAnswer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }

        // ✅ save result
        Result result = new Result();
        result.setStudentId(submission.getStudentId());
        result.setExamId(submission.getExamId());
        result.setScore(score);

        Result saved = resultRepository.save(result);

        // ✅ response
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("studentId", saved.getStudentId());
        response.put("examId", saved.getExamId());
        response.put("score", saved.getScore());
        response.put("total", questions.size());
        response.put("correctAnswers", correctAnswers);

        return ResponseEntity.ok(response);
    }
}