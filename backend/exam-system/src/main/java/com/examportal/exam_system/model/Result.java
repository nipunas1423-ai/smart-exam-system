package com.examportal.exam_system.model;

import jakarta.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long examId;
    private int score;

    public Result(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
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