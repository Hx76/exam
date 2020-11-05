package com.onlineexam.service;

import com.onlineexam.entities.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamService {
    List<Exam> showAllExam(int currentPage,int pageSize);
    List<Exam> showExamByUser(@Param("email") String email);
    Integer countAll();
    Integer updateExam(Exam exam);
    Integer addExam(Exam exam);
}
