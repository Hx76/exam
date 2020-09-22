package com.onlineexam.service.impl;

import com.onlineexam.dao.UserDao;
import com.onlineexam.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public String confirm(@Param("email") String email, String password) {
        System.out.println("********************");
        System.out.println("邮箱："+email+",密码："+password);
        return userDao.checkPassword(email,password);
    }
}
