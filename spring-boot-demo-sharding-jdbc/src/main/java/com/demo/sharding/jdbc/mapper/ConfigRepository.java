package com.demo.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sharding.jdbc.model.TConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ConfigRepository
 * @Description
 * @Author H
 * @Date 2021/10/11 14:21
 * @Version 1.0
 */
@Mapper
public interface ConfigRepository extends BaseMapper<TConfig> {
}
