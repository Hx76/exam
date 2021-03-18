package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.Score;
import com.onlineexam.entities.UserScore;
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
    public CommonResult getUserScore(@PathVariable String email,@PathVariable int examId){
        UserScore score = service.getUserScore(email,examId);
        return new CommonResult(10,"yes",score);
    }


}
