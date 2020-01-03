package com.demo.codegen.utils;

import com.demo.codegen.entity.TableRequest;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DbUtil
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:58
 * @Version 1.0
 */
@Slf4j
@UtilityClass
public class DbUtil {

    public HikariDataSource buildFromTableRequest(TableRequest request) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(request.getPrepend() + request.getUrl());
        dataSource.setUsername(request.getUsername());
        dataSource.setPassword(request.getPassword());
        return dataSource;
    }

}
