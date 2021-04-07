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
public class InformationController {
    @Value("${service-url.nacos-user-service}")
    public String INFORMATION_URL;
    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("显示个人信息")
    @GetMapping(value = "/information/show/{email}")
    public CommonResult showInformation(@PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/show/"+email,CommonResult.class);
    }

    @ApiOperation("更新个人信息")
    @GetMapping(value = "/information/update/{username}/{email}")
    public CommonResult updateInformation(@PathVariable @ApiParam("用户名") String username,
                                          @PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/update/"+username+"/"+email,CommonResult.class);
    }

    @ApiOperation("分页展示所有用户")
    @GetMapping(value = "/information/showAllUsers/{currentPage}/{pageSize}")
    public CommonResult showAllUsers(@PathVariable @ApiParam("当前页") int currentPage,
                                     @PathVariable @ApiParam("页面容量") int pageSize){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/showAllUsers/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @ApiOperation("统计所有用户的数量")
    @GetMapping(value = "/information/countAllUsers")
    public CommonResult countAllUsers(){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/countAllUsers",CommonResult.class);
    }

    @ApiOperation("获取浏览量")
    @GetMapping(value = "/information/pageView")
    public CommonResult pageView(){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/pageView",CommonResult.class);
    }

    @ApiOperation("获取用户名")
    @GetMapping(value = "/information/getUserName/{email}")
    public CommonResult getUserName(@PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(INFORMATION_URL+"/provider/information/getUserName/"+email,CommonResult.class);
    }

}
