package com.onlineexam.service.impl;

import com.onlineexam.dao.UserDao;
import com.onlineexam.entities.User;
import com.onlineexam.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public String getPassword(String email) {
        return userDao.getPassword(email);
    }

    @Override
    public String confirm(@Param("email") String email, String password) {
        System.out.println("********************");
        System.out.println("邮箱："+email+",密码："+password);
        return userDao.checkPassword(email,password);
    }

    @Override
    public User queryByEmail(String email) {
        return userDao.queryByEmail(email);
    }

    @Override
    public String updateClientInfo(User user) {
        String ip = user.getIp();
        String address = user.getAddress();
        String os = user.getOs();
        String device = user.getDevice();
        String browser = user.getBrowser();
        String email = user.getEmail();
        System.out.println(ip+address+os+device+browser+email);
        userDao.renewalClientInfo(ip,address,os,device,browser,email);
        return "更新完成";
    }
}
