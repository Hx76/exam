package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ExamQuestionController {
    @Value("${service-url.nacos-user-service}")
    public String EXAM_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/exam/showExamQuestion/{serialNumber}")
    public CommonResult showExamQuestion(@PathVariable int serialNumber){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showExamQuestion/"+serialNumber,CommonResult.class);
    }
}
