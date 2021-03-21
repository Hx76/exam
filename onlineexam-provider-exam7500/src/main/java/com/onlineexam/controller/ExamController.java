package com.onlineexam.controller;

import com.onlineexam.entities.*;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.CommonResult;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class ExamController {
    @Resource
    private ExamService service;

    @Resource
    private RestHighLevelClient client;

    //获取考试时间
    @GetMapping(value = "/provider/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable int examId){
        Date examTime = service.showExamTime(examId);
        return new CommonResult(10,"yes",examTime);
    }

    //分页展示全部考试
    @GetMapping(value = "/provider/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage, @PathVariable int pageSize){
        List<Exam> exams = service.showAllExam(currentPage,pageSize);
        return new CommonResult(10,"yes",exams);
    }

    //分页展示我参加的考试
    @GetMapping(value = "/provider/exam/showMyJoinExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyJoinExam(@PathVariable int currentPage, @PathVariable int pageSize,@PathVariable String email){
        List<UserScore> exams = service.showMyJoinExam(currentPage,pageSize,email);
        return new CommonResult(10,"yes",exams);
    }

    //查询全部考试的数量
    @GetMapping(value = "/provider/exam/countAll")
    public CommonResult countAllExam(){
        Integer num = service.countAll();
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

    //查询我参加的考试的数量
    @GetMapping(value = "/provider/exam/countMyJoinExam/{email}")
    public CommonResult countMyJoinExam(@PathVariable String email){
        Integer num = service.countMyJoinExam(email);
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

//    @PostMapping(value = "/provider/exam/update")
//    public CommonResult updateInformation(@RequestBody Exam exam){
//
//    }
//


    //分页展示我创建的考试
    @GetMapping(value = "/provider/exam/showMyCreatedExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyCreatedExam(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String email){
        List<Exam> exams = service.showMyCreatedExam(currentPage,pageSize,email);
        return new CommonResult(10,"",exams);
    }

    //查询我创建考试的数量
    @GetMapping(value = "/provider/exam/countMyCreatedExam/{email}")
    public CommonResult countMyCreatedExam(@PathVariable String email){
        Integer num = service.countMyCreatedExam(email);
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

    //获取用户名
    @GetMapping(value = "/provider/exam/getUserName/{email}")
    public CommonResult getUserName(@PathVariable String email){
        String userName = service.getUserName(email);
        return new CommonResult(12,"yes",userName);
    }

    //提交试卷
    @PostMapping(value = "/provider/exam/submit/{examId}/{email}")
    public CommonResult submit(@PathVariable String examId,@PathVariable String email,@RequestBody SubmitQuestion[] question){
        service.submit(question, email, examId);
        return new CommonResult(12,"yes",0);
    }

    @GetMapping("/add/{name}")
    public CommonResult add(@PathVariable String name) throws IOException {
        ExamElasticsearch exam = new ExamElasticsearch(name);
        IndexRequest indexRequest = new IndexRequest("exams")
                .id("1")
                .source("user", exam.getName());
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        return new CommonResult(12,"yes",0);
    }

}
