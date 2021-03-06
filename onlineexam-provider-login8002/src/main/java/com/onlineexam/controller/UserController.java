package com.onlineexam.controller;

import com.onlineexam.entities.User;
import com.onlineexam.service.UserService;

import com.onlineexam.util.JwtUtil;
import com.onlineexam.utils.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/provider/login/{email}/{password}")
    public CommonResult checkPwd(@PathVariable String email, @PathVariable String password){
        System.out.println("邮箱："+email+",密码："+password);
        String pwd = userService.confirm(email,password);
        System.out.println("查出来的密码："+pwd);
        if (pwd==null){
            return new CommonResult(500,"该邮箱未注册","8002");
        }
        if (pwd.equals(password)){
            return new CommonResult(200,"密码正确","8002");
        }else {
            return new CommonResult(444,"密码错误","8002");
        }
    }

    @GetMapping(value = "/Unauthorized")
    public CommonResult unauthorized(){
        return new CommonResult(777,"没有权限",null);
    }


    @PostMapping(value = "/provider/login")
    public CommonResult login(@RequestBody User user){
        try {
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getEmail(), user.getPassword());
            //执行登录的方法 没有异常就成功了
            subject.login(usernamePasswordToken);
            Map<String,String> paylod = new HashMap<>();
            paylod.put("email",user.getEmail());
            paylod.put("password",user.getPassword());
            String token = JwtUtil.getToken(paylod);
            System.out.println("token:"+token);
            userService.updateClientInfo(user);
            return new CommonResult(200,"密码正确",token);
        } catch (UnknownAccountException e) {
            /**
             * 异常信息
             * UnknownAccountException ：用户名不存在
             * IncorrectCredentialsException：密码错误
             */
            e.printStackTrace();
            return new CommonResult(500,"该邮箱未注册","8001");
        }catch (IncorrectCredentialsException e){
            return new CommonResult(444,"密码错误","8001");
        }
    }

}
