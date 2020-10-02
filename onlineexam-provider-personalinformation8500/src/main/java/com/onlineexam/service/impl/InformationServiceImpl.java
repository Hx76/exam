package com.onlineexam.service.impl;

import com.onlineexam.dao.InformationDao;
import com.onlineexam.service.InformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private InformationDao dao;
    @Override
    public String showInformation(String email) {
        return dao.showInformation(email);
    }

    @Override
    public String updateInformation(String username, String email) {
        int num = dao.updateInformation(username, email);
        System.out.println(num);
        return "成功";
    }
}
