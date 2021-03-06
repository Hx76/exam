package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import com.onlineexam.utils.VerificationCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VerificationCodeController {
    @ResponseBody
    @GetMapping(value = "/provider/login/getCode")
    public CommonResult getVerificationCode(HttpServletRequest httpServletRequest){
        String verificationCode = VerificationCodeUtil.generateVerifyCode(4);
        return new CommonResult(505,"验证码已生成",verificationCode);
    }
}
