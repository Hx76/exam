package com.onlineexam.service;

import org.apache.ibatis.annotations.Param;

import javax.mail.MessagingException;

public interface RegisterService {
    boolean checkEmail(@Param("email") String email);
    void sendEmail(@Param("email") String email,String verificationCode) throws MessagingException;
    int register(@Param("email") String email, String password);
}
