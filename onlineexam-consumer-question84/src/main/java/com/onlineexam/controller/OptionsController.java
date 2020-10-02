package com.onlineexam.controller;

import com.onlineexam.entities.Options;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OptionsController {
    @Value("${service-url.nacos-user-service}")
    public String QUESTION_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/options/show/{questionId}")
    public CommonResult showOptions(@PathVariable Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/show/"+questionId,CommonResult.class);
    }

    @PostMapping(value = "/options/update")
    public CommonResult updateQuestion(@RequestBody Options option){
        return restTemplate.postForObject(QUESTION_URL+"/provider/options/update",option,CommonResult.class);
    }

    @PostMapping(value = "/options/add")
    public CommonResult addQuestion(@RequestBody Options option){
        return restTemplate.postForObject(QUESTION_URL+"/provider/options/add",option,CommonResult.class);
    }

    @GetMapping(value = "/options/delete/{optionId}")
    public CommonResult deleteQuestion(@PathVariable Integer optionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/delete/"+optionId,CommonResult.class);
    }
}
