package com.onlineexam.controller;

import com.onlineexam.utils.CommonResult;
import com.onlineexam.service.InformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InformationController {
    @Resource
    InformationService service;

    @GetMapping(value = "/provider/information/show/{email}")
    public CommonResult getPaymentById(@PathVariable String email){
        String userName = service.showInformation(email);
        if (userName==null){
            return new CommonResult(510,"用户名为空","用户名");
        } else {
            return new CommonResult(464,"查询成功",userName);
        }
    }

    @GetMapping(value = "/provider/information/update/{username}/{email}")
    public CommonResult getPaymentById(@PathVariable String username,@PathVariable String email){
        service.updateInformation(username,email);
        return new CommonResult(510,"修改成功","8500");
    }
}
