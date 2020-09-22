package com.onlineexam.service.impl;

import com.onlineexam.dao.RegisterDao;
import com.onlineexam.service.RegisterService;
import com.onlineexam.utils.EmailUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RegisterDao dao;
    @Override
    public boolean checkEmail(String email) {
        System.out.println("********");
        System.out.println(dao.checkEmail(email));
        if (dao.checkEmail(email)==null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void sendEmail(String email,String verificationCode) throws MessagingException {
        EmailUtil.sendEmail(email,verificationCode);
    }

    @Override
    public int register(String email,String password) {
        return dao.register(email,password);
    }
}
