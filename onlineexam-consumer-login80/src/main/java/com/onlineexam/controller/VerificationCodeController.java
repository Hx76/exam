package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RefreshScope //支持Nacos的动态刷新功能
public class VerificationCodeController {
    @Value("${service-url.nacos-user-service}")
    public String LOGIN_URL;

    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/login/getCode")
    public CommonResult getCode(){
        System.out.println("登录界面验证码啊啊啊啊啊啊");
        return restTemplate.getForObject(LOGIN_URL+"/provider/login/getCode",CommonResult.class);
    }
}
