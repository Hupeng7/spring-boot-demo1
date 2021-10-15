package com.demo.sharding.jdbc.algorithm.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @ClassName MyDBPreciseShardingAlgorithm
 * @Description
 * @Author H
 * @Date 2021/10/11 16:52
 * @Version 1.0
 */
public class MyDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {

        /**
         * databaseNames 所有分片库的集合
         * shardingValue 为分片属性，其中logicTableName为逻辑表，columnName分片键（字段）,value为从SQL中解析出的分片键的值
         */
        for (String databaseName : databaseNames) {
            String value = shardingValue.getValue() % databaseNames.size() + "";
            if (databaseName.endsWith(value)) {
                return databaseName;
            }
        }
        throw new IllegalArgumentException();
    }
}
