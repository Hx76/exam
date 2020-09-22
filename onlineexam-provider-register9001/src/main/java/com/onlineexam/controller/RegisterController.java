package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import com.onlineexam.service.RegisterService;
import com.onlineexam.utils.VerificationCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
public class RegisterController {
    @Resource
    private RegisterService registerService;

    @GetMapping(value = "/provider/register/{email}/{password}")
    public CommonResult register(@PathVariable String email, @PathVariable String password){
        System.out.println("密码是："+password);
        if (registerService.register(email,password)==1){
            return new CommonResult(520,"注册成功","9001");
        }else {
            return new CommonResult(520,"注册失败","9001");
        }
    }
    @GetMapping(value = "/provider/register/send/{email}")
    public CommonResult sendEmail(@PathVariable String email) throws MessagingException {
        if (registerService.checkEmail(email)){
            String verificationCode = VerificationCodeUtil.generateVerifyCode(6);
            registerService.sendEmail(email,verificationCode);
            return new CommonResult(510,"验证码已经发送",verificationCode);
        }else {
            return new CommonResult(422,"该邮箱已被注册","9001");
        }

    }
    @GetMapping(value = "/provider/register/checkEmail/{email}")
    public CommonResult checkEmail(@PathVariable String email){
        System.out.println("**********");
        System.out.println("返回值："+registerService.checkEmail(email));
        if (registerService.checkEmail(email)){
            return new CommonResult(500,"该邮箱未注册","9001");
        }else {
            return new CommonResult(422,"该邮箱已注册","9001");
        }
    }
}
