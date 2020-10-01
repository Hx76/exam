package com.onlineexam.dao;

import com.onlineexam.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    String checkPassword(@Param("email") String email, String password);
    User queryByEmail(@Param("email") String email);
    String getPassword(@Param("email") String email);
}
