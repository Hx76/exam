package com.onlineexam.service;

import com.onlineexam.entities.Exam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExamQuestionService {
    List<Integer> showExamQuestion(@PathVariable int serialNumber);
}
