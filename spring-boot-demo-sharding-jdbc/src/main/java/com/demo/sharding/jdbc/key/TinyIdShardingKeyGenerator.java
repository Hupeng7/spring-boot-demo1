package com.demo.sharding.jdbc.key;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @ClassName TinyIdShardingKeyGenerator
 * @Description
 * @Author H
 * @Date 2021/10/11 14:58
 * @Version 1.0
 */
@Component
public class TinyIdShardingKeyGenerator implements ShardingKeyGenerator {
    @Override
    public Comparable<?> generateKey() {
        // Long id = TinyId.nextId("order");
        return null;
    }

    @Override
    public String getType() {
        return "tinyid";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
