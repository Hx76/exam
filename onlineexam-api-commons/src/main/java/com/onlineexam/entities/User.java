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
@ApiModel("用户实体类")
public class User implements Serializable {
    @ApiModelProperty("用户编号")
    private Integer id;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("用户权限")
    private String perm;
    @ApiModelProperty("用户上次登录的Ip")
    private String ip;
    @ApiModelProperty("用户上次登录的地区")
    private String address;
    @ApiModelProperty("用户上次登录的游览器")
    private String browser;
    @ApiModelProperty("用户上次登录的操作系统")
    private String os;
    @ApiModelProperty("用户上次登录的设备")
    private String device;
    @ApiModelProperty("用户的注册日期")
    private Date register_date;
}
