package com.examportal.exam_system.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Result {

    @Id
    private String id;

    private String studentId;
    private String examId;
    private int score;

    public Result(){}

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getStudentId(){
        return studentId;
    }

    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public String getExamId(){
        return examId;
    }

    public void setExamId(String examId){
        this.examId = examId;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }
}