package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("选项实体类")
public class Options implements Serializable {
    @ApiModelProperty("选项编号")
    private Integer id;
    @ApiModelProperty("选项内容")
    private String option;
    @ApiModelProperty("选项编号（A还是B还是C）")
    private String option_number;
    @ApiModelProperty("题目编号")
    private Integer question_id;
}
