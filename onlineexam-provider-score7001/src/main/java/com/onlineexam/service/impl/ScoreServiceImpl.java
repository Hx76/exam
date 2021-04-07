package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.ScoreDao;
import com.onlineexam.entities.*;
import com.onlineexam.service.ScoreService;
import com.onlineexam.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreDao dao;

    @Override
    public List<Score> showAllScore(int currentPage, int pageSize) {
        System.out.println(currentPage+"hahahahahha"+pageSize);
        PageHelper.startPage(currentPage, pageSize);
        List<Score> scores = dao.showAllScore();
        System.out.println(scores);
        int countNums = dao.countScores();
        PageBean<Score> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(scores);
        return pageData.getItems();
    }

    @Override
    public int countScores() {
        return dao.countScores();
    }

    @Override
    public List<UserScore> getExamInfoByExamId(String examId) {
        return dao.getExamInfoByExamId(examId);
    }

    @Override
    public UserScore getUserScore(String email, int examId) {
        int rank = dao.getRank(email);
        UserScore userScore = dao.getUserScore(email, examId);
        userScore.setRank(rank);
        return userScore;
    }

    @Override
    public ExamInfo getExamInfo(Integer examId,String email) {
        final ExamInfo examInfo = new ExamInfo();
        examInfo.setName(dao.getExamName(examId));
        examInfo.setNumber_of_people(dao.getNumberOfPeople(examId));
        examInfo.setRank(dao.getRank(email));
        return examInfo;
    }

    @Override
    public String getAnswer(Integer id) {
        return dao.getAnswer(id);
    }

    @Override
    public List<Report> getReport(Integer examId) {
        return dao.getReport(examId);
    }

    @Override
    public String getUserAnswer(Integer questionId, String email, Integer examId) {
        return dao.getUserAnswer(questionId, email, examId);
    }

}
