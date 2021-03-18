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

    @GetMapping(value = "/information/show/{email}")
    public CommonResult showInformation(@PathVariable String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/show/"+email,CommonResult.class);
    }

    @GetMapping(value = "/information/update/{username}/{email}")
    public CommonResult updateInformation(@PathVariable String username,@PathVariable String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/update/"+username+"/"+email,CommonResult.class);
    }

    @GetMapping(value = "/information/showAllUsers/{currentPage}/{pageSize}")
    public CommonResult showAllUsers(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/showAllUsers/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @GetMapping(value = "/information/countAllUsers")
    public CommonResult countAllUsers(){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/countAllUsers",CommonResult.class);
    }

}
