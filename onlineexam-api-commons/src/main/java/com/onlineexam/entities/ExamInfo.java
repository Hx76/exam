package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamInfo {
    private Integer number_of_people;
    private Integer rank;
    private String name;
}
