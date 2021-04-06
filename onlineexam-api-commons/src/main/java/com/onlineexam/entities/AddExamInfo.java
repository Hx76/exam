package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ApiModel("添加考试实体类")
public class AddExamInfo {
    @ApiModelProperty("考试名称")
    private String name;
    @ApiModelProperty("考试时长")
    private String duration;
    @ApiModelProperty("考试开始年月日")
    private String date;
    @ApiModelProperty("考试开始时分秒")
    private String time;
    @ApiModelProperty("考试创建者")
    private String creator;
    @ApiModelProperty("考试创建时间")
    private String creationTime;
}
