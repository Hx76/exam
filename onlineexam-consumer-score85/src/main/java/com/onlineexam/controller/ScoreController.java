package com.onlineexam.controller;

import com.onlineexam.entities.ExamInfo;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
@RestController
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
    public CommonResult getUserScore(@PathVariable String email,@PathVariable Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getUserScore/"+email+"/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/score/countAllExam")
    public CommonResult countAllExam(){
        return restTemplate.getForObject(Score_URL+"/provider/score/countAllExam",CommonResult.class);
    }

    //获取考试参赛人数，考试名称
    @GetMapping(value = "/score/getExamInfo/{examId}/{email}")
    public CommonResult getEamInfo(@PathVariable Integer examId,@PathVariable String email){
        return restTemplate.getForObject(Score_URL+"/provider/score/getExamInfo/"+examId+"/"+email,CommonResult.class);
    }

    // 获取题目答案
    @GetMapping(value = "/score/getAnswer/{id}")
    public CommonResult getAnswer(@PathVariable Integer id){
        System.out.println("来这里了");
        return restTemplate.getForObject(Score_URL+"/provider/score/getAnswer/"+id,CommonResult.class);
    }

    @GetMapping(value = "/score/getReport/{examId}")
    public CommonResult getReport(@PathVariable Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getReport/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/score/getUserAnswer/{questionId}/{email}/{examId}")
    public CommonResult getUserAnswer(@PathVariable Integer questionId,@PathVariable String email,@PathVariable Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getUserAnswer/"+questionId+"/"+email+"/"+examId,CommonResult.class);
    }


}
