package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaper {
    private Integer id;
    private String email;
    private Integer exam_id;
    private Integer question_id;
    private String answer;
}
