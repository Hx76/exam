package com.onlineexam.dao;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.SubmitQuestion;
import com.onlineexam.entities.UserScore;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExamDao {
    List<Exam> showAllExam();
    int countExams();
    List<Exam> showExamByUser(@Param("email") String email);
    Integer updateExam(Exam exam);
    Integer addExam(Exam exam);
    List<Integer> showExamQuestion(int serialNumber);
    Date showExamTime(@Param("examId") int examId);
    String getUserName(@Param("email") String email);
    Integer countMyExam(@Param("email") String email);
    Integer submit(Integer id,String answer, @Param("email") String email, @Param("examId") String examId);
    Integer submitScore(@Param("email") String email, @Param("examId") String examId,Integer score,Integer sumScore);
    //我创建的考试
    Integer countMyCreatedExam(@Param("email") String email);
    List<Exam> showMyCreatedExam(@Param("email") String email);
    //我参加的考试
    Integer countMyJoinExam(@Param("email") String email);
    List<UserScore> showMyJoinExam(@Param("email") String email);

}
