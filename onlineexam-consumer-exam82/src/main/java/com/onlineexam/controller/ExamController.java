package com.onlineexam.controller;

import com.onlineexam.entities.AddExamInfo;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import com.onlineexam.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@RestController
public class ExamController {
    @Value("${service-url.nacos-user-service}")
    public String EXAM_URL;
    @Resource
    private RestTemplate restTemplate;

    //查询考试时间
    @ApiOperation("查询考试时间")
    @GetMapping(value = "/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable @ApiParam("考试邀请码") int examId){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showExamTime/"+examId,CommonResult.class);
    }

    //分页展示全部考试
    @ApiOperation("分页获取全部考试")
    @GetMapping(value = "/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable @ApiParam("当前页") int currentPage,@PathVariable @ApiParam("页面容量") int pageSize){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showAll/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    //分页展示我参加的考试
    @ApiOperation("分页获取我参加的考试")
    @GetMapping(value = "/exam/showMyJoinExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyJoinExam(@PathVariable @ApiParam("当前页") int currentPage,
                                       @PathVariable @ApiParam("页面容量") int pageSize,
                                       @PathVariable @ApiParam("用户邮箱") String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showMyJoinExam/"+currentPage+"/"+pageSize+"/"+email,CommonResult.class);
    }

    //查询一共多少考试
    @ApiOperation("查询一共多少考试")
    @GetMapping(value = "/exam/countAll")
    public CommonResult countAllExam(){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countAll",CommonResult.class);
    }

    //查询我参加过的考试
    @ApiOperation("查询我参加过的考试")
    @GetMapping(value = "/exam/countMyJoinExam/{email}")
    public CommonResult countMyJoinExam(@PathVariable @ApiParam("用户邮箱") String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countMyJoinExam/"+email,CommonResult.class);
    }

    @ApiOperation("更新考试信息")
    @PostMapping(value = "/exam/update")
    public CommonResult updateInformation(@RequestBody @ApiParam("考试实体类") Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam",exam,CommonResult.class);
    }

    @ApiOperation("创建考试")
    @PostMapping(value = "/exam/create")
    public CommonResult showInformation(@RequestBody @ApiParam("考试实体类") Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/create",exam,CommonResult.class);
    }

    //分页显示我创建的考试
    @ApiOperation("分页获取我创建的考试")
    @GetMapping(value = "/exam/showMyCreatedExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyCreatedExam(@PathVariable @ApiParam("当前页") int currentPage,
                                          @PathVariable @ApiParam("页面容量") int pageSize,
                                          @PathVariable @ApiParam("用户邮箱") String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showMyCreatedExam/"+currentPage+"/"+pageSize+"/"+email,CommonResult.class);
    }

    //查询我创建的考试次数
    @ApiOperation("查询我创建的考试次数")
    @GetMapping(value = "/exam/countMyCreatedExam/{email}")
    public CommonResult countMyCreatedExam(@PathVariable @ApiParam("用户邮箱") String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countMyCreatedExam/"+email,CommonResult.class);
    }

    //获取用户名
    @ApiOperation("获取用户名")
    @GetMapping(value = "/exam/getUserName/{email}")
    public CommonResult getUserName(@PathVariable @ApiParam("用户邮箱") String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/getUserName/"+email,CommonResult.class);
    }

    //提交试卷
    @ApiOperation("提交试卷")
    @PostMapping(value = "/exam/submit/{examId}/{email}")
    public CommonResult submit(@PathVariable @ApiParam("考试邀请码") String examId,
                               @PathVariable @ApiParam("用户邮箱") String email,
                               @RequestBody  @ApiParam("用户提交的题目") SubmitQuestion[] question){
        System.out.println("haha");
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/submit/"+examId+"/"+email,question,CommonResult.class);
    }

    //添加考试
    @ApiOperation("创建考试")
    @PostMapping("/exam/addExam/{value}")
    public CommonResult addExam(@RequestBody @ApiParam("添加考试实体类") AddExamInfo examInfo,
                                @PathVariable @ApiParam("考试题目") List<String> value){
        System.out.println("来这了啊");
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/addExam/"+value,examInfo,CommonResult.class);
    }

    //添加题目到考试里面
    @ApiOperation("添加题目到考试里面")
    @GetMapping("/exam/addExamQuestion/{examIds}/{value}")
    public CommonResult addExamQuestion(@PathVariable @ApiParam("考试邀请码") Integer examIds,
                                        @PathVariable @ApiParam("题目") List<String> value){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/addExamQuestion/"+examIds+"/"+value,CommonResult.class);
    }
}
