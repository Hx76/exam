package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.ExamDao;
import com.onlineexam.entities.*;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.PageBean;
import com.onlineexam.utils.VerificationCodeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //创建考试
    @Override
    public Integer createExam(AddExamInfo examInfo) throws ParseException {
        Integer id = Integer.valueOf(VerificationCodeUtil.generateVerifyCode(7));
        String s1 = examInfo.getDate();
        String s2 = examInfo.getTime();
        System.out.println(s1+","+s2);
        String date = s1+" "+s2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(date);
        Date date1 = simpleDateFormat.parse(date);
        System.out.println("日期："+date1);
        dao.addExam(id,examInfo.getName(),examInfo.getDuration(),date1,examInfo.getCreator(), "未开始",new Date());
        System.out.println("编号："+id);
        return id;
    }

    //添加考试题目
    @Override
    public void addExamQuestion(Integer id, List<String> value) {
        System.out.println("外面id:"+id);
        Integer total = 0;
        for (int i = 0; i < value.size(); i++) {
            String s = value.get(i);
            int len = s.length();
            if (i==0){
                s=s.substring(1,len);
            }
            if (i== value.size()-1){
                s=s.substring(0,len-1);
            }
            System.out.println("题目："+s);
            System.out.println("题目编号："+dao.getQuestionId(s));
            System.out.println("id:"+id);
            dao.addExamQuestion(id,dao.getQuestionId(s));
            total += dao.getQuestionScore(dao.getQuestionId(s));
        }
        dao.updateTotal(id,total);
    }

    @Override
    public List<Exam> search(List<Integer> examIds, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Exam> exams = new ArrayList<Exam>();
        for (Integer examId : examIds) {
            System.out.println("考试编号："+examId);
            exams.add(dao.getExamById(examId));
        }
        int countNums = exams.size();
        PageBean<Exam> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(exams);
        return pageData.getItems();
    }


}
