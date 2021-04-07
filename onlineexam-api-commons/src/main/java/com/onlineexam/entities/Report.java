package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("成绩单实体类")
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @ApiModelProperty("排名")
    private Integer rank;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("成绩")
    private Integer score;
}
