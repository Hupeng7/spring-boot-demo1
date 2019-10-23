package com.demo.dynamic.datasource.controller;

import com.demo.dynamic.datasource.annotation.DefaultDatasource;
import com.demo.dynamic.datasource.datasource.DatasourceConfigCache;
import com.demo.dynamic.datasource.mapper.DatasourceConfigMapper;
import com.demo.dynamic.datasource.model.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DatasourceConfigController
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 16:53
 * @Version 1.0
 */

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DatasourceConfigController {

    private final DatasourceConfigMapper configMapper;

    @PostMapping("/config")
    @DefaultDatasource
    public DatasourceConfig insertConfig(@RequestBody DatasourceConfig config) {
        configMapper.insertUseGeneratedKeys(config);
        DatasourceConfigCache.INSTANCE.addConfig(config.getId(), config);
        return config;
    }

    @DeleteMapping("/config/{id}")
    @DefaultDatasource
    public void removeConfig(@PathVariable Long id) {
        configMapper.deleteByPrimaryKey(id);
        DatasourceConfigCache.INSTANCE.removeConfig(id);
    }
}




