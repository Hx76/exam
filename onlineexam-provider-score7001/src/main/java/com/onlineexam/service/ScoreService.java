package com.onlineexam.service;

import com.onlineexam.entities.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreService {
    List<Score> showAllScore(int currentPage,int pageSize);
    int countScores();
    List<UserScore> getExamInfoByExamId(@Param("examId") String examId);
    UserScore getUserScore(@Param("email") String email, @Param("examId") int examId);
    ExamInfo getExamInfo(@Param("examId") Integer examId,@Param("email") String email);
    String getAnswer(Integer id);
    List<Report> getReport(@Param("examId") Integer examId);
    String getUserAnswer(Integer questionId,String email,Integer examId);
}
