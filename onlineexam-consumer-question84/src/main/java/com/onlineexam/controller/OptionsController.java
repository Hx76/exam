package com.onlineexam.controller;

import com.onlineexam.entities.Options;
import com.onlineexam.utils.CommonResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("展示问题选项")
    @GetMapping(value = "/options/show/{questionId}")
    public CommonResult showOptions(@PathVariable @ApiParam("问题编号") Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/show/"+questionId,CommonResult.class);
    }

    @ApiOperation("修改题目")
    @PostMapping(value = "/options/update")
    public CommonResult updateQuestion(@RequestBody @ApiParam("选项实体类") Options option){
        return restTemplate.postForObject(QUESTION_URL+"/provider/options/update",option,CommonResult.class);
    }

    @ApiOperation("添加选项")
    @PostMapping(value = "/options/add")
    public CommonResult addQuestion(@RequestBody @ApiParam("选项实体类") Options option){
        return restTemplate.postForObject(QUESTION_URL+"/provider/options/add",option,CommonResult.class);
    }

    @ApiOperation("删除选项")
    @GetMapping(value = "/options/delete/{optionId}")
    public CommonResult deleteQuestion(@PathVariable @ApiParam("选项实体类") Integer optionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/delete/"+optionId,CommonResult.class);
    }
}
