package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class QuestionController {
    @Value("${service-url.nacos-user-service}")
    public String QUESTION_URL;
    @Resource
    private RestTemplate restTemplate;
    @GetMapping(value = "/question/showAll")
    public CommonResult showAll(){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/show",CommonResult.class);
    }

    @GetMapping(value = "/question/showByUser/{email}")
    public CommonResult showQuestionByUser( @PathVariable String email){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/show/"+email,CommonResult.class);
    }

    @GetMapping(value = "/question/showByExam/{examId}")
    public CommonResult showQuestionByExam(@PathVariable String examId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/show/"+examId,CommonResult.class);
    }

    @GetMapping(value = "/question/update/{examId}/{questionBody}/{answer}")
    public CommonResult updateQuestion(@PathVariable String examId,@PathVariable String questionBody, @PathVariable String answer){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/update/"+examId+"/"+questionBody+"/"+answer,CommonResult.class);
    }

    @GetMapping(value = "/question/delete/{examId}")
    public CommonResult deleteQuestion(@PathVariable String examId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/information/show/"+examId,CommonResult.class);
    }
}
