package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.ExamDao;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import com.onlineexam.entities.UserScore;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public Integer countAll() {
        return dao.countExams();
    }

    @Override
    public Integer updateExam(Exam exam) {
        return dao.updateExam(exam);
    }

    @Override
    public Integer addExam(Exam exam) {
        return dao.addExam(exam);
    }

    @Override
    public Date showExamTime(int examId) {
        return dao.showExamTime(examId);
    }

    @Override
    public String getUserName(String email) {
        return dao.getUserName(email);
    }

    @Override
    public Integer submit(SubmitQuestion[] question, String email, String examId) {
        int sumScore=0;
        int score=0;
        for (int i=1;i< question.length;i++){
            dao.submit(question[i].getQuestion_id(),question[i].getSubmitAnswer(),email,examId);
            if (question[i].getSubmitAnswer().equals(question[i].getAnswer())){
                score+=question[i].getScore();
            }
            sumScore+=question[i].getScore();
        }
        System.out.println("得分："+score);
        System.out.println("总得分："+sumScore);
        dao.submitScore(email,examId,score,sumScore);
        return 0;
    }

    @Override
    public Integer countMyCreatedExam(String email) {
        return dao.countMyCreatedExam(email);
    }

    @Override
    public List<Exam> showMyCreatedExam(int currentPage, int pageSize,@Param("email") String email) {
        PageHelper.startPage(currentPage, pageSize);
        List<Exam> exams = dao.showMyCreatedExam(email);
        System.out.println("我创建的考试"+exams);
        int countNums = dao.countMyCreatedExam(email);
        PageBean<Exam> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(exams);
        return pageData.getItems();
    }

    @Override
    public Integer countMyJoinExam(String email) {
        return dao.countMyJoinExam(email);
    }

    @Override
    public List<UserScore> showMyJoinExam(int currentPage, int pageSize, @Param("email") String email) {
        PageHelper.startPage(currentPage, pageSize);
        List<UserScore> exams = dao.showMyJoinExam(email);
        System.out.println("所有我参加过的考试"+exams);
        int countNums = dao.countMyJoinExam(email);
        PageBean<UserScore> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(exams);
        return pageData.getItems();
    }

    @Override
    public Void createExam(Exam exam) throws IOException {
        return null;
    }


}
