package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("考试实体类")
public class Exam implements Serializable {
    @ApiModelProperty("考试编号")
    private Integer id;
    @ApiModelProperty("考试邀请码")
    private Integer exam_serial_number;
    @ApiModelProperty("考试名称")
    private String name;
    @ApiModelProperty("考试时长")
    private String duration;
    @ApiModelProperty("考试开始时间")
    private Date start_time;
    @ApiModelProperty("考试创建者")
    private String creator;
    @ApiModelProperty("考试状态")
    private String state;
    @ApiModelProperty("考试总分")
    private String total_points;
    @ApiModelProperty("考试创建时间")
    private String creation_time;
    @ApiModelProperty("参加考试人数")
    private String number_of_people;
}
