package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("题目实体类")
public class Question implements Serializable {
    @ApiModelProperty("题目编号")
    private Integer id;
    @ApiModelProperty("题干")
    private String question_body;
    @ApiModelProperty("题目类型")
    private String type_id;
    @ApiModelProperty("题目创建人")
    private String creator;
    @ApiModelProperty("题目分值")
    private Integer score;
    @ApiModelProperty("题目选项")
    private Options[] options;
    private Options[] option;
    @ApiModelProperty("题目答案")
    private String answer;
}
