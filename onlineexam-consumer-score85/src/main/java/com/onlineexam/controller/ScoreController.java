package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

public class ScoreController {
    @Value("${service-url.nacos-user-service}")
    public String Score_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/score/getAllExamInfo/{currentPage}/{pageSize}")
    public CommonResult getAllExamInfo(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(Score_URL+"/provider/score/getAllExamInfo/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @GetMapping(value = "/score/getExamInfoByExamId/{examId}")
    public CommonResult getExamInfoByExamId(@PathVariable String examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getAllExamInfo/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/score/getUserScore/{email}/{examId}")
    public CommonResult getUserScore(@PathVariable String email,@PathVariable int examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getAllExamInfo/"+email+"/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/score/countAllExam")
    public CommonResult countAllExam(){
        return restTemplate.getForObject(Score_URL+"/provider/score/countAllExam",CommonResult.class);
    }




}