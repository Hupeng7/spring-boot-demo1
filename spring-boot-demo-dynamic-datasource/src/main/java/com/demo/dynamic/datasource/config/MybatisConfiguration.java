package com.demo.dynamic.datasource.config;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @ClassName MybatisConfiguration
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 16:30
 * @Version 1.0
 */

@Configuration
@MapperScan(basePackages = "com.demo.dynamic.datasource.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfiguration {

    /**
     * 创建会话工厂
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    @SneakyThrows
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


}
