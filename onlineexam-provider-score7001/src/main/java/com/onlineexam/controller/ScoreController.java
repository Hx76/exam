package com.onlineexam.controller;

import com.onlineexam.entities.*;
import com.onlineexam.service.ScoreService;
import com.onlineexam.utils.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ScoreController {
    @Resource
    private ScoreService service;

    @GetMapping(value = "/provider/score/getAllExamInfo/{currentPage}/{pageSize}")
    public CommonResult getAllExamInfo(@PathVariable int currentPage, @PathVariable int pageSize){
        List<Score> scores = service.showAllScore(currentPage, pageSize);
        return new CommonResult(10,"yes",scores);
    }

    @GetMapping(value = "/provider/score/getExamInfoByExamId/{examId}")
    public CommonResult getExamInfoByExamId(@PathVariable String examId){
        List<UserScore> score = service.getExamInfoByExamId(examId);
        return new CommonResult(10,"yes",score);
    }

    @GetMapping(value = "/provider/score/countAllExam")
    public CommonResult countAllExam(){
        return new CommonResult(10,"yes",service.countScores());
    }

    @GetMapping(value = "/provider/score/getUserScore/{email}/{examId}")
    public CommonResult getUserScore(@PathVariable String email,@PathVariable Integer examId){
        System.out.println("获取用户成绩");
        UserScore score = service.getUserScore(email,examId);
        return new CommonResult(10,"yes",score);
    }

    //获取考试参赛人数，排名，考试名称
    @GetMapping(value = "/provider/score/getExamInfo/{examId}/{email}")
    public CommonResult getExamInfo(@PathVariable Integer examId,@PathVariable String email){
        final ExamInfo examInfo = service.getExamInfo(examId,email);
        return new CommonResult(10,"yes",examInfo);
    }

    //获取题目答案
    @GetMapping(value = "/provider/score/getAnswer/{id}")
    public CommonResult getAnswer(@PathVariable Integer id){
        final String answer = service.getAnswer(id);
        return new CommonResult(10,"yes",answer);
    }

    //获取名次，用户名，邮箱，分数
    @GetMapping(value = "/provider/score/getReport/{examId}")
    public CommonResult getReport(@PathVariable Integer examId){
        final List<Report> report = service.getReport(examId);
        return new CommonResult(10,"yes",report);
    }

    //获取用户答案
    @GetMapping(value = "/provider/score/getUserAnswer/{questionId}/{email}/{examId}")
    public CommonResult getUserAnswer(@PathVariable Integer questionId,@PathVariable String email,@PathVariable Integer examId){
        return new CommonResult(10,"yes",service.getUserAnswer(questionId, email, examId));
    }


}
