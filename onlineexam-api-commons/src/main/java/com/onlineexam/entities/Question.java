package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {
    private Integer id;
    private String question_body;
    private String type_id;
    private String creator;
    private Integer score;
    private Options[] options;
}
