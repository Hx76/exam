package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamQuestionController {
    private ExamService service;

    @GetMapping(value = "/provider/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage, @PathVariable int pageSize){
        List<Exam> exams = service.showAllExam(currentPage,pageSize);
        return new CommonResult(10,"yes",exams);
    }
    @GetMapping(value = "/provider/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage, @PathVariable int pageSize){
        List<Exam> exams = service.showAllExam(currentPage,pageSize);
        return new CommonResult(10,"yes",exams);
    }
}
