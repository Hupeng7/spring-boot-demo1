package com.demo.sharding.jdbc.algorithm.tableAlgorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName MyTableHintShardingAlgorithm
 * @Description hint分表算法
 * @Author H
 * @Date 2021/10/12 11:16
 * @Version 1.0
 */
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, HintShardingValue<String> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String tableName : tableNames) {
            Collection<String> values = hintShardingValue.getValues();
            for (String shardingValue : values) {
                if (tableName.endsWith(String.valueOf(Long.valueOf(shardingValue) % tableNames.size()))) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }
}
