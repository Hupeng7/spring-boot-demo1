package com.demo.orm.beetlsql.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @ClassName BeetlConfig
 * @Description Beetl数据源配置
 * @Author Leo
 * @Date 2019/11/27 14:34
 * @Version 1.0
 */
@Configuration
public class BeetlConfig {

    /**
     * Beetl需要显示的配置数据源 方可启动项目 大坑 切记
     *
     * @param env
     * @return
     */
    @Bean(name = "datasource")
    public DataSource getDataSource(Environment env) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
