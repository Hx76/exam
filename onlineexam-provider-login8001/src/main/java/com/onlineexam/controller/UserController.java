package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import com.onlineexam.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/provider/login/{email}/{password}")
    public CommonResult getPaymentById(@PathVariable String email, @PathVariable String password){
        System.out.println("邮箱："+email+",密码："+password);
        String pwd = userService.confirm(email,password);
        System.out.println("查出来的密码："+pwd);
        if (pwd==null){
            return new CommonResult(500,"该邮箱未注册","8001");
        }
        if (pwd.equals(password)){
            return new CommonResult(200,"密码正确","8001");
        }else {
            return new CommonResult(444,"密码错误","8001");
        }
    }


}
