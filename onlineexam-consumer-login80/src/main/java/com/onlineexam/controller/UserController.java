package com.onlineexam.controller;

import com.onlineexam.entities.User;
import com.onlineexam.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("验证密码是否正确")
    @GetMapping(value = "/login/{email}/{password}")
        public CommonResult confirm(@PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(LOGIN_URL+"/provider/login/"+email+"/"+password,CommonResult.class);
    }

    @ApiOperation("处理用户登录请求并返回token")
    @PostMapping(value = "/login")
    public CommonResult confirm(@RequestBody @ApiParam("用户对象") User user){
        System.out.println("信息："+user.getEmail()+"密码："+user.getPassword());
        return restTemplate.postForObject(LOGIN_URL+"/provider/login",user,CommonResult.class);
    }

    @ApiOperation("登出系统")
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

}
