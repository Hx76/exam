package com.onlineexam.service.impl;

import com.onlineexam.dao.OptionsDao;
import com.onlineexam.entities.Options;
import com.onlineexam.service.OptionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OptionsServiceImpl implements OptionsService {
    @Resource
    private OptionsDao dao;

    @Override
    public List<Options> showOption(Integer questionId) {
        return dao.showOption(questionId);
    }

    @Override
    public Integer updateOption(Options options) {
        return dao.updateOption(options);
    }

    @Override
    public Integer deleteOption(Integer optionId) {
        return dao.deleteOption(optionId);
    }

    @Override
    public Integer addOption(Options options) {
        return dao.addOption(options);
    }
}
