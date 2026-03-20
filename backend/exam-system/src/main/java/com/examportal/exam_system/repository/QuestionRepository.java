package com.examportal.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.exam_system.model.Question;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByExamId(Long examId);

}