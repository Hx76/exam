package com.onlineexam.controller;

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

    @GetMapping("/provider/question/showAll/{currentPage}/{pageSize}")
    public CommonResult showAll(@PathVariable int currentPage,@PathVariable int pageSize){
        List<Question> questions = service.showAll(currentPage, pageSize);
        return new CommonResult(12,"yes",questions);
    }

    @GetMapping(value = "/provider/question/showByExam/{examId}")
    public CommonResult showQuestionByExam(@PathVariable Integer examId){
        List<Question> questions = service.showQuestionByExam(examId);
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

}
