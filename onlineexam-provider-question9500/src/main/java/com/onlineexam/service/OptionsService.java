package com.onlineexam.service;

import com.onlineexam.entities.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionsService {
    List<Options> showOption(@Param("questionId") Integer questionId);
    Integer updateOption(Options options);
    Integer deleteOption(@Param("optionId") Integer optionId);
    Integer addOption(Options options);
}
