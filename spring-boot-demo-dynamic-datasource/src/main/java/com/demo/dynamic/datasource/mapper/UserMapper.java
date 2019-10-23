package com.demo.dynamic.datasource.mapper;

import com.demo.dynamic.datasource.config.MyMapper;
import com.demo.dynamic.datasource.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leo
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}
