package com.onlineexam.dao;

import com.onlineexam.entities.ExamInfo;
import com.onlineexam.entities.Report;
import com.onlineexam.entities.Score;
import com.onlineexam.entities.UserScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreDao {
    List<Score> showAllScore();
    int countScores();
    List<UserScore> getExamInfoByExamId(@Param("examId") String examId);
    UserScore getUserScore(@Param("email") String email, @Param("examId") int examId);
    String getExamName(@Param("examId") Integer examId);
    Integer getNumberOfPeople(@Param("examId") Integer examId);
    Integer getRank(@Param("email") String email);
    String getAnswer(Integer id);
    List<Report> getReport(Integer examId);
    String getUserAnswer(Integer questionId,String email,Integer examId);
}
