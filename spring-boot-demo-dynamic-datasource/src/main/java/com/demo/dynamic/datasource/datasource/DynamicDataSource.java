package com.demo.dynamic.datasource.datasource;

import com.demo.dynamic.datasource.model.DatasourceConfig;
import com.demo.dynamic.datasource.utils.SpringUtil;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName DynamicDataSource
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 15:49
 * @Version 1.0
 */

@Slf4j
public class DynamicDataSource extends HikariDataSource {
    @Override
    public Connection getConnection() throws SQLException {
        // 获取当前数据源 id
        Long id = DatasourceConfigContextHolder.getCurrentDatasourceConfig();
        // 根据当前id 获取数据源
        HikariDataSource dataSource = DatasourceHolder.INSTANCE.getDatasource(id);

        if (null == dataSource) {
            dataSource = initDatasource(id);
        }
        return dataSource.getConnection();
    }

    /**
     * 初始化数据源
     *
     * @param id
     * @return
     */
    private HikariDataSource initDatasource(Long id) {
        HikariDataSource dataSource = new HikariDataSource();
        // 判断是否是默认数据源
        if (DatasourceHolder.DEFAULT_ID.equals(id)) {
            // 默认数据源根据 application.yml 配置的生成
            DataSourceProperties properties = SpringUtil.getBean(DataSourceProperties.class);
            dataSource.setJdbcUrl(properties.getUrl() );
            dataSource.setUsername(properties.getUsername());
            dataSource.setPassword(properties.getPassword());
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        } else {
            // 不是默认数据源，通过缓存获取对应id的数据源的配置
            DatasourceConfig datasourceConfig = DatasourceConfigCache.INSTANCE.getConfig(id);
            if (datasourceConfig == null) {
                throw new RuntimeException("无此数据源");
            }
            dataSource.setJdbcUrl(datasourceConfig.buildJdbUrl() + "&serverTimezone=UTC");
            dataSource.setUsername(datasourceConfig.getUsername());
            dataSource.setPassword(datasourceConfig.getPassword());
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        }
        DatasourceHolder.INSTANCE.addDatasource(id, dataSource);
        return dataSource;
    }
}
