package com.demo.dynamic.datasource.config;

import com.demo.dynamic.datasource.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DatasourceConfiguration
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/21 18:53
 * @Version 1.0
 */

@Configuration
public class DatasourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(DynamicDataSource.class);
        return dataSourceBuilder.build();
    }
}
