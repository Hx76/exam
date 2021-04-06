package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("试卷实体类")
public class ExamPaper {
    @ApiModelProperty("考试编号")
    private Integer id;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("考试邀请码")
    private Integer exam_id;
    @ApiModelProperty("题目编号")
    private Integer question_id;
    @ApiModelProperty("题目答案")
    private String answer;
}
