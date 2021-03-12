package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ExamController {
    @Value("${service-url.nacos-user-service}")
    public String EXAM_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable int examId){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showExamTime/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showAll/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @GetMapping(value = "/exam/countAll")
    public CommonResult countQuestion(){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countAll",CommonResult.class);
    }

    @PostMapping(value = "/exam/update")
    public CommonResult updateInformation(@RequestBody Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam",exam,CommonResult.class);
    }

    @PostMapping(value = "/exam/create")
    public CommonResult showInformation(@RequestBody Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/create",exam,CommonResult.class);
    }

    @GetMapping(value = "/exam/showByUser/{email}")
    public CommonResult updateInformation(@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/information/show/"+email,CommonResult.class);
    }
}
