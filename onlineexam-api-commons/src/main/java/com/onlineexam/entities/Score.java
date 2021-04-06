package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("成绩实体类")
public class Score {
    @ApiModelProperty("考试编号")
    private Integer exam_serial_number;
    @ApiModelProperty("考试名称")
    private String name;
    @ApiModelProperty("考试开始时间")
    private Date start_time;
    @ApiModelProperty("考试总分")
    private int total_points;
    @ApiModelProperty("参加考试人数")
    private int number_of_people;
}
