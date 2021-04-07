package com.onlineexam.service;

import com.onlineexam.entities.Exam;
import com.onlineexam.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InformationService {
    String showInformation(@Param("email") String email);
    String updateInformation(@Param("username") String username, @Param("email") String email);
    List<User> showAllUsers(int currentPage, int pageSize);
    Integer countAll();
    String getUserName(String email);
}
