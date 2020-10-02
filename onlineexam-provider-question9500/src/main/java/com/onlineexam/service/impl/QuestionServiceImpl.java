package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.QuestionDao;
import com.onlineexam.entities.Question;
import com.onlineexam.service.QuestionService;
import com.onlineexam.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao dao;

    @Override
    public List<Question> showAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Question> questions = dao.showAll();
        System.out.println(questions);
        int countNums = dao.countQuestions();
        PageBean<Question> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(questions);
        return pageData.getItems();
    }

    @Override
    public List<Question> showQuestionByExam(Integer examId) {
        return dao.showQuestionByExam(examId);
    }

    @Override
    public Integer updateQuestion(Question question) {
        return dao.updateQuestion(question);
    }

    @Override
    public Integer deleteQuestion(Integer questionId) {
        return dao.deleteQuestion(questionId);
    }

}
