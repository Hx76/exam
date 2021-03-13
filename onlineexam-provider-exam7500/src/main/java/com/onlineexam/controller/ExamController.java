package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class ExamController {
    @Resource
    private ExamService service;

    @GetMapping(value = "/provider/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable int examId){
        Date examTime = service.showExamTime(examId);
        return new CommonResult(10,"yes",examTime);
    }

    @GetMapping(value = "/provider/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage, @PathVariable int pageSize){
        List<Exam> exams = service.showAllExam(currentPage,pageSize);
        return new CommonResult(10,"yes",exams);
    }

    @GetMapping(value = "/provider/exam/countAll")
    public CommonResult countQuestion(){

        Integer num = service.countAll();
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

//    @PostMapping(value = "/provider/exam/update")
//    public CommonResult updateInformation(@RequestBody Exam exam){
//
//    }
//
//    @PostMapping(value = "/provider/exam/create")
//    public CommonResult showInformation(@RequestBody Exam exam){
//
//    }
//
    @GetMapping(value = "/provider/exam/showByUser/{email}")
    public CommonResult updateInformation(@PathVariable String email){
        return new CommonResult(10,"","");
    }


}
