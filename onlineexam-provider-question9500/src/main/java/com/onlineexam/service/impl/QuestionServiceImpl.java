package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.QuestionDao;
import com.onlineexam.entities.Question;
import com.onlineexam.service.QuestionService;
import com.onlineexam.utils.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao dao;

    @Override
    public List<Question> showAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Question> questions = dao.showAll();
        System.out.println(questions);
        for (int i=0;i<questions.size();i++){
            Question question = questions.get(i);
            if (question.getType_id().equals("1")){
                question.setType_id("选择题");
            }else if (question.getType_id().equals("2")){
                question.setType_id("判断题");
            }else {
                question.setType_id("填空题");
            }
        }
        int countNums = dao.countQuestions();
        PageBean<Question> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(questions);
        return pageData.getItems();
    }

    @Override
    public List<Question> showQuestionByExam(Integer examId) {
        return dao.showQuestionByExam(examId);
    }

    @Override
    public Integer updateQuestion(Question question) {
        return dao.updateQuestion(question);
    }

    @Override
    public Integer deleteQuestion(Integer questionId) {
        return dao.deleteQuestion(questionId);
    }

    @Override
    public Integer countAll() {
        return dao.countQuestions();
    }

    @Override
    public List<Question> showSelectionQuestion(@Param("examId") Integer examId) {
        List<Question> selectionQuestions = dao.showSelectionQuestion(examId);
        return selectionQuestions;
    }

    @Override
    public List<Question> showJudgeQuestion(@Param("examId") Integer examId) {
        List<Question> judgeQuestions = dao.showJudgeQuestion(examId);
        return judgeQuestions;
    }

    @Override
    public List<Question> showFillingQuestion(@Param("examId") Integer examId) {
        List<Question> FillingQuestions = dao.showFillingQuestion(examId);
        return FillingQuestions;
    }

}
