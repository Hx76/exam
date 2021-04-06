package com.onlineexam.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("返回结果类")
public class CommonResult<T> {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("数据")
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
