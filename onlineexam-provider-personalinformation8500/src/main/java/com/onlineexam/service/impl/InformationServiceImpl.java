package com.onlineexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.onlineexam.dao.InformationDao;
import com.onlineexam.entities.Exam;
import com.onlineexam.entities.Question;
import com.onlineexam.entities.User;
import com.onlineexam.service.InformationService;
import com.onlineexam.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<User> showAllUsers(int currentPage, int pageSize) {
        System.out.println(currentPage+"hahahahahha"+pageSize);
        PageHelper.startPage(currentPage, pageSize);
        List<User> users = dao.showAllUsers();
        System.out.println(users);
        int countNums = dao.countAll();
        PageBean<User> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(users);
        return pageData.getItems();
    }

    @Override
    public Integer countAll() {
        return dao.countAll();
    }

    @Override
    public String getUserName(String email) {
        return dao.getUserName(email);
    }

    @Override
    public User getAdminInfo() {
        return dao.getAdminInfo();
    }

    @Override
    public List<User> search(List<String> emails, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> users = new ArrayList<User>();
        for (String email : emails) {
            users.add(dao.getUserByEmail(email));
        }
        int countNums = emails.size();
        PageBean<User> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(users);
        return pageData.getItems();
    }

}
