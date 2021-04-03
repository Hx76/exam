package com.onlineexam.service;

import com.onlineexam.entities.AddExamInfo;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import com.onlineexam.entities.UserScore;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ExamService {
    List<Exam> showAllExam(int currentPage,int pageSize);
    Integer countAll();
    Integer updateExam(Exam exam);
    Date showExamTime(@Param("examId") int examId);
    String getUserName(@Param("email") String email);
    Integer submit(SubmitQuestion[] question,String email,String examId);
    //我创建的考试
    Integer countMyCreatedExam(@Param("email") String email);
    List<Exam> showMyCreatedExam(int currentPage,int pageSize,@Param("email") String email);
    //我参加的考试
    Integer countMyJoinExam(@Param("email") String email);
    List<UserScore> showMyJoinExam(int currentPage, int pageSize, @Param("email") String email);
    Integer createExam(AddExamInfo examInfo) throws ParseException;
    void addExamQuestion(Integer id,List<String> value);

}
