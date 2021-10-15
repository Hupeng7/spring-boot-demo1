package com.demo.sharding.jdbc.algorithm.tableAlgorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @ClassName MyTableComplexKeyShardingAlgorithm
 * @Description 自定义复合分表策略
 * @Author H
 * @Date 2021/10/12 11:11
 * @Version 1.0
 */
public class MyTableComplexKeyShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        return null;
    }
}
