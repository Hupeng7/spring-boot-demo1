package com.demo.sharding.jdbc.algorithm.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName MyDBHintShardingAlgorithm
 * @Description
 * @Author H
 * @Date 2021/10/11 16:21
 * @Version 1.0
 */
public class MyDBHintShardingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> databaseNames, HintShardingValue<String> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String databaseName : databaseNames) {
            Collection<String> values = hintShardingValue.getValues();

            for (String shardingValue : values) {
                if (databaseName.endsWith(String.valueOf(Long.valueOf(shardingValue) % databaseNames.size()))) {
                    result.add(databaseName);
                }
            }
        }
        return result;
    }
}
