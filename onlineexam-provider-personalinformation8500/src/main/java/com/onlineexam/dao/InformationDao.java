package com.onlineexam.dao;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationDao {
    String showInformation(@Param("email") String email);
    int updateInformation(@Param("username") String username, @Param("email") String email);
    List<User> showAllUsers();
    Integer countAll();
}
