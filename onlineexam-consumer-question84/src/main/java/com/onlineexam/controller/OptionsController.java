package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OptionsController {
    @Value("${service-url.nacos-user-service}")
    public String QUESTION_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/options/show/{questionId}")
    public CommonResult showOptions(@PathVariable String questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/show/"+questionId,CommonResult.class);
    }

    @GetMapping(value = "/options/update/{optionId}/{option}/{optionNumber}")
    public CommonResult updateQuestion(@PathVariable String optionId,@PathVariable String option, @PathVariable String optionNumber){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/update/"+optionId+"/"+option+"/"+optionNumber,CommonResult.class);
    }

    @GetMapping(value = "/options/delete/{optionId}")
    public CommonResult deleteQuestion(@PathVariable String optionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/options/delete/"+optionId,CommonResult.class);
    }
}
