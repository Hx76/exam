package com.onlineexam.controller;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ExamController {
    @Value("${service-url.nacos-user-service}")
    public String EXAM_URL;
    @Resource
    private RestTemplate restTemplate;

    //查询考试时间
    @GetMapping(value = "/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable int examId){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showExamTime/"+examId,CommonResult.class);
    }

    //分页展示全部考试
    @GetMapping(value = "/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showAll/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    //分页展示我参加的考试
    @GetMapping(value = "/exam/showMyJoinExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyJoinExam(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showMyJoinExam/"+currentPage+"/"+pageSize+"/"+email,CommonResult.class);
    }

    //查询一共多少考试
    @GetMapping(value = "/exam/countAll")
    public CommonResult countAllExam(){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countAll",CommonResult.class);
    }

    //查询我参加过的考试
    @GetMapping(value = "/exam/countMyJoinExam/{email}")
    public CommonResult countMyJoinExam(@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countMyJoinExam/"+email,CommonResult.class);
    }

    //
    @PostMapping(value = "/exam/update")
    public CommonResult updateInformation(@RequestBody Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam",exam,CommonResult.class);
    }

    //
    @PostMapping(value = "/exam/create")
    public CommonResult showInformation(@RequestBody Exam exam){
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/create",exam,CommonResult.class);
    }

    //分页显示我创建的考试
    @GetMapping(value = "/exam/showMyCreatedExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyCreatedExam(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/showMyCreatedExam/"+currentPage+"/"+pageSize+"/"+email,CommonResult.class);
    }

    //查询我创建的考试次数
    @GetMapping(value = "/exam/countMyCreatedExam/{email}")
    public CommonResult countMyCreatedExam(@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/countMyCreatedExam/"+email,CommonResult.class);
    }

    //获取用户名
    @GetMapping(value = "/exam/getUserName/{email}")
    public CommonResult getUserName(@PathVariable String email){
        return restTemplate.getForObject(EXAM_URL+"/provider/exam/getUserName/"+email,CommonResult.class);
    }

    //提交试卷
    @PostMapping(value = "/exam/submit/{examId}/{email}")
    public CommonResult submit(@PathVariable String examId,@PathVariable String email,@RequestBody SubmitQuestion[] question){
        System.out.println("haha");
        return restTemplate.postForObject(EXAM_URL+"/provider/exam/submit/"+examId+"/"+email,question,CommonResult.class);
    }
}
