package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitQuestion implements Serializable {
    private Integer question_id;
    private Integer score;
    private String answer;
    private String submitAnswer;
}
