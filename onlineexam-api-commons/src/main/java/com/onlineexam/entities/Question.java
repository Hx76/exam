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
    private String questionBody;
    private String typeId;
    private String examId;

}
