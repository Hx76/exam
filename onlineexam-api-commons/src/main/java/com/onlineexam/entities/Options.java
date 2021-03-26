package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Options implements Serializable {
    private Integer id;
    private String option;
    private String option_number;
    private Integer question_id;
}
