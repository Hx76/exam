package com.onlineexam.service;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExamService {
    List<Exam> showAllExam(int currentPage,int pageSize);
    Integer countAll();
    Integer updateExam(Exam exam);
    Integer addExam(Exam exam);
    Date showExamTime(@Param("examId") int examId);
    String getUserName(@Param("email") String email);
    Integer countMyExam(@Param("email") String email);
    List<Exam> showMyExam(int currentPage,int pageSize,String email);
    Integer submit(SubmitQuestion[] question,String email,String examId);
}
