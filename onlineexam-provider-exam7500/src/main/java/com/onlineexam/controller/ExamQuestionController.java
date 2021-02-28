package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.service.ExamQuestionService;
import com.onlineexam.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ExamQuestionController {
    @Resource
    private ExamQuestionService service;

    @GetMapping(value = "/provider/exam/showExamQuestion/{serialNumber}")
    public CommonResult showExamQuestion(@PathVariable int serialNumber){
        List<Integer> exams = service.showExamQuestion(serialNumber);
        return new CommonResult(10,"yes",exams);
    }

}
