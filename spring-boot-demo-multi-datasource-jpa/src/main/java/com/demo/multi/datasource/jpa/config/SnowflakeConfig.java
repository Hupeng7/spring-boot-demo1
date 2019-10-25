package com.demo.multi.datasource.jpa.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SnowflakeConfig
 * @Description 雪花算法生成器
 * @Author Leo
 * @Date 2019/10/24 15:19
 * @Version 1.0
 */
@Configuration
public class SnowflakeConfig {
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}
