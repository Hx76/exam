package com.onlineexam.controller;

import com.onlineexam.entities.User;
import com.onlineexam.utils.CommonResult;
import com.onlineexam.service.InformationService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class InformationController {
    @Resource
    InformationService service;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/provider/information/show/{email}")
    public CommonResult showUserInformation(@PathVariable String email){
        String userName = service.showInformation(email);
        if (userName==null){
            return new CommonResult(510,"用户名为空","用户名");
        } else {
            return new CommonResult(464,"查询成功",userName);
        }
    }

    @GetMapping(value = "/provider/information/update/{username}/{email}")
    public CommonResult updateUserInformation(@PathVariable String username,@PathVariable String email){
        service.updateInformation(username,email);
        return new CommonResult(510,"修改成功","8500");
    }

    @GetMapping(value = "/provider/information/showAllUsers/{currentPage}/{pageSize}")
    public CommonResult showAllUsers(@PathVariable int currentPage,@PathVariable int pageSize){
        List<User> userList = service.showAllUsers(currentPage, pageSize);
        return new CommonResult(510,"修改成功",userList);
    }

    @GetMapping(value = "/provider/information/countAllUsers")
    public CommonResult countAllUsers(){
        return new CommonResult(510,"查询成功",service.countAll());
    }

    @GetMapping(value = "/provider/information/pageView")
    public CommonResult pageView(){
        return new CommonResult(510,"查询成功",stringRedisTemplate.opsForValue().get("views"));
    }
}
