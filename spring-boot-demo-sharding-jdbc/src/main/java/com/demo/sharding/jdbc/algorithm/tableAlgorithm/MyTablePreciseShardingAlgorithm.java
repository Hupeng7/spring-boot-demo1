package com.demo.sharding.jdbc.algorithm.tableAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @ClassName MyTablePreciseShardingAlgorithm
 * @Description 自定义标准分表策略
 * @Author H
 * @Date 2021/10/12 11:29
 * @Version 1.0
 */
public class MyTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> preciseShardingValue) {
        /**
         * tableNames 对应分片库中所有分片表的集合
         * shardingValue 为分片属性，其中，logicTableName 为逻辑表，columnName分片键（字段），value为从SQL中解析出的分片键的值
         */
        for (String tableName : tableNames) {
            /**
             * 取模算法，分片键 % 表数量
             */
            String value = preciseShardingValue.getValue() % tableNames.size() + "";
            if (tableName.endsWith(value)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }
}
