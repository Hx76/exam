package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserScore {
    private Integer id;
    private Integer exam_id;
    private String email;
    private Integer score;
    private Integer sum_score;
}
