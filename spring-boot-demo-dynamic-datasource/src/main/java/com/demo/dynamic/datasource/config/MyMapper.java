package com.demo.dynamic.datasource.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName MyMapper
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 16:16
 * @Version 1.0
 */
@RegisterMapper
public interface MyMapper<T> extends Mapper<T>, MySqlMapper {
}
