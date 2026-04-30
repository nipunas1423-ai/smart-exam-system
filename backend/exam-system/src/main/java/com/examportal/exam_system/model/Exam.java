package com.examportal.exam_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exams")
public class Exam {

    @Id
    private String id;   // Mongo uses String/ObjectId

    private String title;
    private int duration;
    private String teacherId;

    public Exam(){}

    public String getId(){
        return id;
    }

    public void setId(String id){
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

    public String getTeacherId(){
        return teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
}