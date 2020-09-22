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
    public List<Options> showOption(String questionId) {
        return dao.showOption(questionId);
    }

    @Override
    public Integer updateOption(String optionId, String option, String optionNumber) {
        return dao.updateOption(optionId, option, optionNumber);
    }

    @Override
    public Integer deleteOption(String optionId) {
        return dao.deleteOption(optionId);
    }
}
