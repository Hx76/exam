package com.onlineexam.controller;

import com.onlineexam.entities.Options;
import com.onlineexam.entities.Question;
import com.onlineexam.utils.CommonResult;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class QuestionController {
    @Value("${service-url.nacos-user-service}")
    public String QUESTION_URL;
    @Resource
    private RestTemplate restTemplate;

    //展示所有选择题
    @GetMapping(value = "/question/showSelectionQuestion/{examId}")
    public CommonResult showSelectionQuestion(@PathVariable int examId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showSelectionQuestion/"+examId,CommonResult.class);
    }

    //展示所有判断题
    @GetMapping(value = "/question/showJudgeQuestion/{examId}")
    public CommonResult showJudgeQuestion(@PathVariable int examId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showJudgeQuestion/"+examId,CommonResult.class);
    }

    //展示所有填空题
    @GetMapping(value = "/question/showFillingQuestion/{examId}")
    public CommonResult showFillingQuestion(@PathVariable int examId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showFillingQuestion/"+examId,CommonResult.class);
    }

    //分页展示所有题目
    @GetMapping(value = "/question/showAll/{currentPage}/{pageSize}")
    public CommonResult showAll(@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showAll/"+currentPage+"/"+pageSize,CommonResult.class);
    }

    //通过id展示题目
    @GetMapping(value = "/question/showQuestionById/{questionId}")
    public CommonResult showQuestionByExam(@PathVariable Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/showQuestionById/"+questionId,CommonResult.class);
    }

    //修改题目
    @PostMapping(value = "/question/update")
    public CommonResult updateQuestion(@RequestBody Question question){
        return restTemplate.postForObject(QUESTION_URL+"/provider/question/update",question,CommonResult.class);
    }

    //删除题目
    @GetMapping(value = "/question/delete/{questionId}")
    public CommonResult deleteQuestion(@PathVariable Integer questionId){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/delete/"+questionId,CommonResult.class);
    }

    //根据考试统计所有题目
    @GetMapping(value = "/question/countAll/{email}")
    public CommonResult countQuestion(@PathVariable String email){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/countAll/"+email,CommonResult.class);
    }

    //统计所有题目
    @GetMapping(value = "/question/countAll")
    public CommonResult countAll(){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/countAll",CommonResult.class);
    }

    @PostMapping(value = "/question/addQuestion")
    public CommonResult addQuestion(@RequestBody Question question){
        System.out.println("到这了啊");
        return restTemplate.postForObject(QUESTION_URL+"/provider/question/addQuestion",question,CommonResult.class);
    }

    @PostMapping("/question/addOptions")
    public CommonResult addOptions(@RequestBody List<Options> options){
        return restTemplate.postForObject(QUESTION_URL+"/provider/question/addOptions",options,CommonResult.class);
    }

    @GetMapping("/question/search/{key}/{currentPage}/{pageSize}")
    public CommonResult search(@PathVariable String key,@PathVariable int currentPage,@PathVariable int pageSize){
        return restTemplate.getForObject(QUESTION_URL+"/provider/question/search/"+key+"/"+currentPage+"/"+pageSize,CommonResult.class);
    }
}
