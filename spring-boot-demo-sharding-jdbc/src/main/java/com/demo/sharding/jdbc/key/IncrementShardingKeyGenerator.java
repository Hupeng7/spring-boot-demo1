package com.demo.sharding.jdbc.key;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName IncrementShardingKeyGenerator
 * @Description
 * @Author H
 * @Date 2021/10/11 14:46
 * @Version 1.0
 */
public final class IncrementShardingKeyGenerator implements ShardingKeyGenerator {
    @Getter
    private final String type = "INCREMENT";

    private final AtomicInteger count = new AtomicInteger();

    @Getter
    @Setter
    private Properties properties = new Properties();

    @Override
    public Comparable<?> generateKey() {
        return count.incrementAndGet();
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
