package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.ScoreDao;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.Score;
import com.onlineexam.entities.UserScore;
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
        return dao.getUserScore(email, examId);
    }

}
