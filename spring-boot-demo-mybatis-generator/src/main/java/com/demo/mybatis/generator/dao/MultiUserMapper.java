package com.demo.mybatis.generator.dao;

import com.demo.mybatis.generator.entity.MultiUser;

public interface MultiUserMapper {
    int insert(MultiUser record);

    int insertSelective(MultiUser record);
}