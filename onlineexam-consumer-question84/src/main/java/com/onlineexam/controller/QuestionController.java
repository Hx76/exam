package com.onlineexam.controller;

import com.onlineexam.entities.Question;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class QuestionController {
    @Value("${service-url.nacos-user-service}")
    public String QUESTION_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/question/showAll/{currentPage}/{pageSize}")
    public CommonResult showAll(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showAll/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @GetMapping(value = "/question/showQuestionById/{questionId}")
    public CommonResult showQuestionByExam(@PathVariable Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showQuestionById/"+questionId,CommonResult.class);
    }

    @PostMapping(value = "/question/update")
    public CommonResult updateQuestion(@RequestBody Question question){
        return restTemplate.postForObject(QUESTION_URL+"/provider/question/update",question,CommonResult.class);
    }

    @GetMapping(value = "/question/delete/{questionId}")
    public CommonResult deleteQuestion(@PathVariable Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/delete/"+questionId,CommonResult.class);
    }

    @GetMapping(value = "/question/countAll")
    public CommonResult countQuestion(){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/countAll",CommonResult.class);
    }
}
