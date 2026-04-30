package com.examportal.exam_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class Question {

    @Id
    private String id;   // Mongo uses String/ObjectId

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer;

    private String examId;  // changed to String (to match Mongo IDs)

    public Question(){}

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getQuestionText() { return questionText; }

    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }

    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }

    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }

    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }

    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getCorrectAnswer() { return correctAnswer; }

    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getExamId() { return examId; }

    public void setExamId(String examId) { this.examId = examId; }
}