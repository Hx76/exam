package com.onlineexam.controller;

import com.onlineexam.entities.Options;
import com.onlineexam.service.OptionsService;
import com.onlineexam.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OptionsController {
    @Resource
    private OptionsService service;

    @GetMapping(value = "/provider/options/show/{questionId}")
    public CommonResult showOptions(@PathVariable Integer questionId){
        List<Options> options = service.showOption(questionId);
        return new CommonResult(100,"查询成功",options);
    }

    @PostMapping(value = "/provider/options/update")
    public CommonResult updateQuestion(@RequestBody Options option){
        service.updateOption(option);
        return new CommonResult(100,"更新成功",null);
    }

    @PostMapping(value = "/provider/options/add")
    public CommonResult addQuestion(@RequestBody Options option){
        service.addOption(option);
        return new CommonResult(100,"添加成功",null);
    }

    @GetMapping(value = "/provider/options/delete/{optionId}")
    public CommonResult deleteQuestion(@PathVariable Integer optionId){
        service.deleteOption(optionId);
        return new CommonResult(100,"删除成功",null);
    }
}
