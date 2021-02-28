package com.onlineexam.service.impl;

import com.onlineexam.dao.ExamDao;
import com.onlineexam.entities.Exam;
import com.onlineexam.service.ExamQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Resource
    private ExamDao dao;

    @Override
    public List<Integer> showExamQuestion(int serialNumber) {
        return dao.showExamQuestion(serialNumber);
    }
}
