package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class InformationController {
    @Value("${service-url.nacos-user-service}")
    public String INFORMATION_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/information/show/{username}/{email}")
    public CommonResult showInformation(@PathVariable String username,@PathVariable String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/show/"+username+"/"+email,CommonResult.class);
    }

    @GetMapping(value = "/information/update/{username}/{email}")
    public CommonResult updateInformation(@PathVariable String username,@PathVariable String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/update/"+username+"/"+email,CommonResult.class);
    }
}
