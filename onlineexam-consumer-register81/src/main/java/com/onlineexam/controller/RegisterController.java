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
    @ApiOperation("将用户的注册信息存进数据库")
    @GetMapping(value = "/register/{email}/{password}")
    public CommonResult register(@PathVariable @ApiParam("邮箱") String email,
                                 @PathVariable @ApiParam("密码") String password){
        return restTemplate.getForObject(Register_URL+"/provider/register/"+email+"/"+password,CommonResult.class);
    }

    /**
     * 判断邮箱是否被注册
     * @param email
     * @return
     */
    @ApiOperation("判断邮箱是否被注册")
    @GetMapping(value = "/register/checkEmail/{email}")
    public CommonResult confirmEmail(@PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(Register_URL+"/provider/register/confirm/"+email,CommonResult.class);
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @ApiOperation("发送验证码")
    @GetMapping(value = "/register/send/{email}")
    public CommonResult sendEmail(@PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(Register_URL+"/provider/register/send/"+email,CommonResult.class);
    }
}
