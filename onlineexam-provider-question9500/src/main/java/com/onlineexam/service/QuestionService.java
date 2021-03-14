package com.onlineexam.service;

import com.onlineexam.entities.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {
    List<Question> showAll(int currentPage,int pageSize);
    List<Question> showQuestionByExam(@Param("examId") Integer examId);
    Integer updateQuestion(Question question);
    Integer deleteQuestion(@Param("questionId") Integer questionId);
    Integer countAll();
    List<Question> showSelectionQuestion(@Param("examId") Integer examId);
    List<Question> showJudgeQuestion(@Param("examId") Integer examId);
    List<Question> showFillingQuestion(@Param("examId") Integer examId);
    String getPerm(@Param("email") String email);
}
