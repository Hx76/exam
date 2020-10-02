package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam implements Serializable {
    private Integer id;
    private Integer exam_serial_number;
    private String name;
    private String duration;
    private Date start_time;
    private String creator;
    private String state;
    private String total_points;
    private String creation_time;
    private String number_of_people;
}
