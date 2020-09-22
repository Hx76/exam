package com.onlineexam.service;

import com.onlineexam.entities.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionsService {
    List<Options> showOption(@Param("questionId") String questionId);
    Integer updateOption(@Param("email") String optionId,String option,String optionNumber);
    Integer deleteOption(@Param("optionId") String optionId);
}
