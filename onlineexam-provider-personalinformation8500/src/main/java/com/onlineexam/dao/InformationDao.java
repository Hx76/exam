package com.onlineexam.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InformationDao {
    String showInformation(@Param("email") String email);
    String updateInformation(@Param("username") String username, @Param("email") String email);
}
