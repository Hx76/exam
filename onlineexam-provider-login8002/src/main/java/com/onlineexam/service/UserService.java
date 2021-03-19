package com.onlineexam.service;

import com.onlineexam.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    String getPassword(@Param("email") String email);
    String confirm(@Param("email") String email, String password);
    User queryByEmail(@Param("email") String email);
    String updateClientInfo(User user);
}
