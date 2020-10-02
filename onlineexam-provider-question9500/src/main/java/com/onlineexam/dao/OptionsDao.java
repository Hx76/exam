package com.onlineexam.dao;

import com.onlineexam.entities.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionsDao {
    List<Options> showOption(@Param("questionId") Integer questionId);
    Integer updateOption(Options options);
    Integer deleteOption(@Param("optionId") Integer optionId);
    Integer addOption(Options options);
}
