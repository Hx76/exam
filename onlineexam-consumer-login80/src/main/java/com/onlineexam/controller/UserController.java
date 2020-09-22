package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Value("${service-url.nacos-user-service}")
    public String LOGIN_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/login/{email}/{password}")
        public CommonResult confirm(@PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(LOGIN_URL+"/provider/login/"+email+"/"+password,CommonResult.class);
    }
}
