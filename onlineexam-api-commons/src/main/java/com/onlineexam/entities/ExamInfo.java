package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("考试信息实体类")
public class ExamInfo {
    @ApiModelProperty("参加考试人数")
    private Integer number_of_people;
    @ApiModelProperty("排名")
    private Integer rank;
    @ApiModelProperty("考试名称")
    private String name;
}
