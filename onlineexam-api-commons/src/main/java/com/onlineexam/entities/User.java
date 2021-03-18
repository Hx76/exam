package com.onlineexam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String password;
    private String email;
    private String username;
    private String perm;
    private String ip;
    private String address;
    private String browser;
    private String os;
    private String device;
    private Date register_date;
}
