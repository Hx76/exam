package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ExamController {
    @Value("${service-url.nacos-user-service}")
    public String EXAM_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/exam/showAll")
    public CommonResult showAllExam(){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showAll",CommonResult.class);
    }

    @GetMapping(value = "/exam/update/{examSerialNumber}")
    public CommonResult updateInformation(@PathVariable Integer examSerialNumber){
        return restTemplate.getForObject(EXAM_URL+"/provider/information/show/",CommonResult.class);
    }

    @GetMapping(value = "/exam/create/{email}")
    public CommonResult showInformation(@PathVariable String username, @PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(EXAM_URL+"/provider/information/show/"+username+"/"+email+"/"+password,CommonResult.class);
    }

    @GetMapping(value = "/information/update/{username}/{email}/{password}")
    public CommonResult updateInformation(@PathVariable String username,@PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(EXAM_URL+"/provider/information/show/"+username+"/"+email+"/"+password,CommonResult.class);
    }
}
