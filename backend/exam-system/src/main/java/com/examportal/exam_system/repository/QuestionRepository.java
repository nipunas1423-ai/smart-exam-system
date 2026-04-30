package com.examportal.exam_system.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.examportal.exam_system.model.Exam;
import com.examportal.exam_system.model.Question;
import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findByExamId(String examId);

    

}