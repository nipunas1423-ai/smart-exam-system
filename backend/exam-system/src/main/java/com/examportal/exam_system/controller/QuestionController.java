package com.examportal.exam_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examportal.exam_system.model.Question;
import com.examportal.exam_system.repository.QuestionRepository;
import com.examportal.exam_system.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/exam/{examId}")
    public List<Question> getQuestionsByExam(@PathVariable Long examId) {
        return questionRepository.findByExamId(examId);
    }

    // Edit a question
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question updated) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found: " + id));
        q.setQuestionText(updated.getQuestionText());
        q.setOptionA(updated.getOptionA());
        q.setOptionB(updated.getOptionB());
        q.setOptionC(updated.getOptionC());
        q.setOptionD(updated.getOptionD());
        q.setCorrectAnswer(updated.getCorrectAnswer());
        return questionRepository.save(q);
    }

    // Delete a question
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found: " + id));
        questionRepository.delete(q);
        return "Question deleted successfully";
    }
}