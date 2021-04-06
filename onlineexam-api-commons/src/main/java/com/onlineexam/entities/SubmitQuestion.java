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
@ApiModel("用户提交题目实体类")
public class SubmitQuestion implements Serializable {
    @ApiModelProperty("题目编号")
    private Integer question_id;
    @ApiModelProperty("题目分值")
    private Integer score;
    @ApiModelProperty("题目答案")
    private String answer;
    @ApiModelProperty("提交的答案")
    private String submitAnswer;
}
