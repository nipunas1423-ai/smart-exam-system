package com.examportal.exam_system.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Result {

    @Id
    private String id;

    private Long studentId;
    private Long examId;
    private int score;

    public Result(){}

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Long getStudentId(){
        return studentId;
    }

    public void setStudentId(Long studentId){
        this.studentId = studentId;
    }

    public Long getExamId(){
        return examId;
    }

    public void setExamId(Long examId){
        this.examId = examId;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }
}