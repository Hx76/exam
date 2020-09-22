package com.onlineexam.dao;

import com.onlineexam.entities.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionDao {
    List<Question> showAll();
    List<Question> showQuestionByUser(@Param("email") String email, String password);
    List<Question> showQuestionByExam(@Param("email") String email, String password);
    Integer updateQuestion(@Param("email") String email, String password);
    Integer deleteQuestion(@Param("email") String email, String password);
}
