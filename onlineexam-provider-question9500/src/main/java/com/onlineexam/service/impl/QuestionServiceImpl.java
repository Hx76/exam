package com.onlineexam.service.impl;

import com.onlineexam.dao.QuestionDao;
import com.onlineexam.entities.Question;
import com.onlineexam.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao dao;

    @Override
    public List<Question> showAll() {
        return null;
    }

    @Override
    public List<Question> showQuestionByUser(String email, String password) {
        return null;
    }

    @Override
    public List<Question> showQuestionByExam(String email, String password) {
        return null;
    }

    @Override
    public Integer updateQuestion(String email, String password) {
        return null;
    }

    @Override
    public Integer deleteQuestion(String email, String password) {
        return null;
    }
}
