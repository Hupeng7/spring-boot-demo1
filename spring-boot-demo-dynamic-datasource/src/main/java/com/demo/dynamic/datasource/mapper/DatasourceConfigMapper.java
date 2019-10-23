package com.demo.dynamic.datasource.mapper;

import com.demo.dynamic.datasource.config.MyMapper;
import com.demo.dynamic.datasource.model.DatasourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leo
 */
@Mapper
public interface DatasourceConfigMapper extends MyMapper<DatasourceConfig> {
}
