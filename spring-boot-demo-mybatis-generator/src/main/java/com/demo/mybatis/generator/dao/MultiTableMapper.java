package com.demo.mybatis.generator.dao;

import com.demo.mybatis.generator.entity.MultiTable;

public interface MultiTableMapper {
    int insert(MultiTable record);

    int insertSelective(MultiTable record);
}