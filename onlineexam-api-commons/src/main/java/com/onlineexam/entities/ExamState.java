package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamState {
    private Integer exam_serial_number;
    private Date start_time;
    private String duration;
    private String state;
}
