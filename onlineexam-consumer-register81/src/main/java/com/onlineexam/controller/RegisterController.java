package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class RegisterController {
    @Value("${service-url.nacos-user-service}")
    public String Register_URL;
    @Resource
    private RestTemplate restTemplate;

    /**
     * 注册进数据库
     * @param email
     * @param password
     * @return
     */
    @GetMapping(value = "/register/{email}/{password}")
    public CommonResult register(@PathVariable String email, @PathVariable String password){
        return restTemplate.getForObject(Register_URL+"/provider/register/"+email+"/"+password,CommonResult.class);
    }

    /**
     * 判断邮箱是否被注册
     * @param email
     * @return
     */
    @GetMapping(value = "/register/checkEmail/{email}")
    public CommonResult confirmEmail(@PathVariable String email){
        return restTemplate.getForObject(Register_URL+"/provider/register/confirm/"+email,CommonResult.class);
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @GetMapping(value = "/register/send/{email}")
    public CommonResult sendEmail(@PathVariable String email){
        return restTemplate.getForObject(Register_URL+"/provider/register/send/"+email,CommonResult.class);
    }
}
