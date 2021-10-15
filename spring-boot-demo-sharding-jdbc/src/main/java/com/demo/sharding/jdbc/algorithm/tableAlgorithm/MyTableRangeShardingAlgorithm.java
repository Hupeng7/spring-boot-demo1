package com.demo.sharding.jdbc.algorithm.tableAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName MyTableRangeShardingAlgorithm
 * @Description 范围分表算法
 * @Author H
 * @Date 2021/10/12 11:36
 * @Version 1.0
 */
public class MyTableRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, RangeShardingValue<Integer> rangeShardingValue) {
        Set<String> result = new LinkedHashSet<>();
        // between and 的起始值
        int upper = rangeShardingValue.getValueRange().upperEndpoint();
        int lower = rangeShardingValue.getValueRange().lowerEndpoint();
        // 循环范围计算分表逻辑
        for (int i = lower; i < upper; i++) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(i % tableNames.size() + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }
}
