package com.onlineexam.dao;

import com.onlineexam.entities.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionDao {
    List<Question> showAll();
    int countQuestions();
    List<Question> showQuestionByExam(@Param("examId") Integer examId);
    Integer updateQuestion(Question question);
    Integer deleteQuestion(@Param("questionId") Integer questionId);
}
