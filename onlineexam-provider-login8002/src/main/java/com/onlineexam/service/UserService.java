package com.onlineexam.service;

import org.apache.ibatis.annotations.Param;

public interface UserService {
    String confirm(@Param("email") String email, String password);
}
