package com.onlineexam.service;

import org.apache.ibatis.annotations.Param;

public interface InformationService {
    String showInformation(@Param("email") String email);
    String updateInformation(@Param("username") String username, @Param("email") String email);
}
