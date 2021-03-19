package com.onlineexam.controller;

import com.onlineexam.entities.User;
import com.onlineexam.utils.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RefreshScope //支持Nacos的动态刷新功能
public class UserController {
    @Value("${service-url.nacos-user-service}")
    public String LOGIN_URL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/login/{email}/{password}")
        public CommonResult confirm(@PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(LOGIN_URL+"/provider/login/"+email+"/"+password,CommonResult.class);
    }
    @PostMapping(value = "/login")
    public CommonResult confirm(@RequestBody User user){
        System.out.println("信息："+user.getEmail()+"密码："+user.getPassword());
        return restTemplate.postForObject(LOGIN_URL+"/provider/login",user,CommonResult.class);
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

}
