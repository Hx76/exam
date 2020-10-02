package com.onlineexam.dao;

import com.onlineexam.entities.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ExamDao {
    List<Exam> showAllExam();
    int countExams();
    List<Exam> showExamByUser(@Param("email") String email);
    Integer updateExam(Exam exam);
    Integer addExam(Exam exam);

}
