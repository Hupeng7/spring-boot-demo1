package com.demo.mybatis.generator.dao;

import com.demo.mybatis.generator.entity.BindCardRecord;

public interface BindCardRecordMapper {
    int insert(BindCardRecord record);

    int insertSelective(BindCardRecord record);
}