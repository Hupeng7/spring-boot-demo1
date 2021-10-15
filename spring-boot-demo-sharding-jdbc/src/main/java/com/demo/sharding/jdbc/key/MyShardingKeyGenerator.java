package com.demo.sharding.jdbc.key;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyShardingKeyGenerator
 * @Description 自定义主键生成器
 * @Author H
 * @Date 2021/10/11 14:55
 * @Version 1.0
 */
@Component
public class MyShardingKeyGenerator implements ShardingKeyGenerator {
    private final AtomicInteger count = new AtomicInteger();

    /**
     * 核心方法-生成主键ID
     * @return
     */
    @Override
    public Comparable<?> generateKey() {
        return count.incrementAndGet();
    }

    /**
     * 自定义的生成方案类型
     *
     * @return
     */
    @Override
    public String getType() {
        return "XXX";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
