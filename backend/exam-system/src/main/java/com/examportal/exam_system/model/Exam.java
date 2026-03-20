package com.examportal.exam_system.model;

import jakarta.persistence.*;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int duration;

    private Long teacherId;

    public Exam(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public Long getTeacherId(){
        return teacherId;
    }

    public void setTeacherId(Long teacherId){
        this.teacherId = teacherId;
    }
}