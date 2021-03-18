package com.onlineexam.controller;

import com.onlineexam.entities.ClientInfo;
import com.onlineexam.entities.Question;
import com.onlineexam.service.QuestionService;
import com.onlineexam.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class QuestionController {
    @Resource
    private QuestionService service;

    @GetMapping(value = "/provider/question/showSelectionQuestion/{examId}")
    public CommonResult showSelectionQuestion(@PathVariable int examId){
        List<Question> questions = service.showSelectionQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    @GetMapping(value = "/provider/question/showJudgeQuestion/{examId}")
    public CommonResult showJudgeQuestion(@PathVariable int examId){
        List<Question> questions = service.showJudgeQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    @GetMapping(value = "/provider/question/showFillingQuestion/{examId}")
    public CommonResult showFillingQuestion(@PathVariable int examId){
        List<Question> questions = service.showFillingQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    @GetMapping("/provider/question/showAll/{currentPage}/{pageSize}")
    public CommonResult showAll(@PathVariable int currentPage,@PathVariable int pageSize){
        List<Question> questions = service.showAll(currentPage, pageSize);
        return new CommonResult(12,"yes",questions);
    }

    @GetMapping(value = "/provider/question/showQuestionById/{questionId}")
    public CommonResult showQuestionById(@PathVariable Integer questionId){
        List<Question> questions = service.showQuestionByExam(questionId);
        return new CommonResult(12,"yes",questions);
    }

    @PostMapping(value = "/provider/question/update")
    public CommonResult updateQuestion(@RequestBody Question question){
        service.updateQuestion(question);
        return new CommonResult(12,"yes","");
    }

    @GetMapping(value = "/provider/question/delete/{questionId}")
    public CommonResult deleteQuestion(@PathVariable Integer questionId){
        service.deleteQuestion(questionId);
        return new CommonResult(12,"yes","");
    }

    @GetMapping(value = "/provider/question/countAll/{email}")
    public CommonResult countQuestion(@PathVariable String email){
        Integer num = service.countAll();
        System.out.println(num);
        String str = service.getPerm(email);
        return new CommonResult(12,str,num);
    }

    @GetMapping(value = "/provider/question/countAll")
    public CommonResult countAll(){
        Integer num = service.countAll();
        System.out.println(num);
        return new CommonResult(12,"123",num);
    }

}
