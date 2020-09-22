package com.onlineexam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    String checkPassword(@Param("email") String email, String password);
}
