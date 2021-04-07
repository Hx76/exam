package com.onlineexam.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户成绩实体类")
public class UserScore {
    @ApiModelProperty("成绩编号")
    private Integer id;
    @ApiModelProperty("考试邀请码")
    private Integer exam_id;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("得分")
    private Integer score;
    @ApiModelProperty("考试总分")
    private Integer sum_score;
    @ApiModelProperty("排名")
    private Integer rank;
}
