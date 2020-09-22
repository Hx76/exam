package com.onlineexam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterDao {
    String checkEmail(@Param("email") String email);
    int register(@Param("email") String email,@Param("password")String password);
}
