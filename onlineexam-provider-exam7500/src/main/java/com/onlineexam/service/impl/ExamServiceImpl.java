package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.ExamDao;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.Question;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamDao dao;

    @Override
    public List<Exam> showAllExam(int currentPage,int pageSize) {
        System.out.println(currentPage+"hahahahahha"+pageSize);
        PageHelper.startPage(currentPage, pageSize);
        List<Exam> exams = dao.showAllExam();
        System.out.println(exams);
        int countNums = dao.countExams();
        PageBean<Exam> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(exams);
        return pageData.getItems();
    }

    @Override
    public List<Exam> showExamByUser(String email) {
        return dao.showExamByUser(email);
    }

    @Override
    public Integer updateExam(Exam exam) {
        return dao.updateExam(exam);
    }

    @Override
    public Integer addExam(Exam exam) {
        return dao.addExam(exam);
    }
}
