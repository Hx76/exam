package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class AddExamInfo {
    private String name;
    private String duration;
    private String date;
    private String time;
    private String creator;
    private String creationTime;
}
