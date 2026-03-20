package com.examportal.exam_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examportal.exam_system.model.Result;
import com.examportal.exam_system.repository.ResultRepository;
import com.examportal.exam_system.service.ResultService;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

   @Autowired
private ResultService resultService;
@PostMapping("/submit")
public Result submitResult(@RequestBody Result result){
    return resultService.saveResult(result);
}

@GetMapping
public List<Result> getResults(){
    return resultService.getAllResults();
}
@GetMapping("/student/{studentId}")
public List<Result> getResultsByStudent(@PathVariable Long studentId){
    return resultService.getResultsByStudent(studentId);
}
@GetMapping("/exam/{examId}")
public List<Result> getResultsByExam(@PathVariable Long examId){
    return resultService.getResultsByExam(examId);
}
    
}