package com.onlineexam.controller;

import com.onlineexam.entities.ExamInfo;
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
public class ScoreController {
    @Value("${service-url.nacos-user-service}")
    public String Score_URL;
    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("分页获取全部考试")
    @GetMapping(value = "/score/getAllExamInfo/{currentPage}/{pageSize}")
    public CommonResult getAllExamInfo(@PathVariable @ApiParam("当前页") int currentPage,
                                       @PathVariable @ApiParam("页面容量") int pageSize){
        return restTemplate.getForObject(Score_URL+"/provider/score/getAllExamInfo/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    @ApiOperation("分页获取指定考试信息")
    @GetMapping(value = "/score/getExamInfoByExamId/{examId}")
    public CommonResult getExamInfoByExamId(@PathVariable @ApiParam("考试邀请码") String examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getAllExamInfo/"+examId,CommonResult.class);
    }

    @ApiOperation("获取用户指定考试的成绩")
    @GetMapping(value = "/score/getUserScore/{email}/{examId}")
    public CommonResult getUserScore(@PathVariable @ApiParam("邮箱") String email,
                                     @PathVariable @ApiParam("考试邀请码") Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getUserScore/"+email+"/"+examId,CommonResult.class);
    }

    @ApiOperation("查询全部考试数量")
    @GetMapping(value = "/score/countAllExam")
    public CommonResult countAllExam(){
        return restTemplate.getForObject(Score_URL+"/provider/score/countAllExam",CommonResult.class);
    }

    //获取考试参赛人数，考试名称
    @ApiOperation("获取考试参赛人数、名称")
    @GetMapping(value = "/score/getExamInfo/{examId}/{email}")
    public CommonResult getEamInfo(@PathVariable @ApiParam("考试邀请码") Integer examId,
                                   @PathVariable @ApiParam("邮箱") String email){
        return restTemplate.getForObject(Score_URL+"/provider/score/getExamInfo/"+examId+"/"+email,CommonResult.class);
    }

    // 获取题目答案
    @ApiOperation("获取题目答案")
    @GetMapping(value = "/score/getAnswer/{id}")
    public CommonResult getAnswer(@PathVariable @ApiParam("题目编号") Integer id){
        System.out.println("来这里了");
        return restTemplate.getForObject(Score_URL+"/provider/score/getAnswer/"+id,CommonResult.class);
    }

    @ApiOperation("获取指定考试成绩单")
    @GetMapping(value = "/score/getReport/{examId}")
    public CommonResult getReport(@PathVariable @ApiParam("考试邀请码") Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getReport/"+examId,CommonResult.class);
    }

    @ApiOperation("获取用户作答情况")
    @GetMapping(value = "/score/getUserAnswer/{questionId}/{email}/{examId}")
    public CommonResult getUserAnswer(@PathVariable @ApiParam("题目编号") Integer questionId,
                                      @PathVariable @ApiParam("邮箱") String email,
                                      @PathVariable @ApiParam("考试邀请码") Integer examId){
        return restTemplate.getForObject(Score_URL+"/provider/score/getUserAnswer/"+questionId+"/"+email+"/"+examId,CommonResult.class);
    }


}
