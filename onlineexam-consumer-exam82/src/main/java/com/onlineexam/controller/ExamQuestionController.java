package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("获取考试题目")
    @GetMapping(value = "/exam/showExamQuestion/{serialNumber}")
    public CommonResult showExamQuestion(@PathVariable @ApiParam("考试邀请码") int serialNumber){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showExamQuestion/"+serialNumber,CommonResult.class);
    }
}
