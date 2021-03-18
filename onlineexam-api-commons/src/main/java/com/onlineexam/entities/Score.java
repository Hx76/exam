package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Integer exam_serial_number;
    private String name;
    private Date start_time;
    private int total_points;
    private int number_of_people;
}
