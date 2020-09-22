package com.onlineexam.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserService {
    String confirm(@Param("email") String email, String password);

}
