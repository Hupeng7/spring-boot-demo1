package com.demo.sharding.jdbc.algorithm.dbAlgorithm;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MyDBComplexKeysShardingAlgorithm
 * @Description
 * @Author H
 * @Date 2021/10/11 16:05
 * @Version 1.0
 */
public class MyDBComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    @Override
    public Collection<String> doSharding(Collection<String> databaseNames, ComplexKeysShardingValue<Integer> complexKeysShardingValue) {
        // 得到每个分片键对应的值
        Collection<Integer> orderIdValues = this.getShardingValue(complexKeysShardingValue, "order_id");
        Collection<Integer> userIdValues = this.getShardingValue(complexKeysShardingValue, "user_id");

        List<String> shardingSuffix = new ArrayList<>();
        for (Integer userId : userIdValues) {
            for (Integer orderId : orderIdValues) {
                String suffix = userId % 2 + "_" + orderId % 2;
                for (String databaseName : databaseNames) {
                    if (databaseName.endsWith(suffix)) {
                        shardingSuffix.add(databaseName);
                    }
                }
            }
        }
        return shardingSuffix;
    }

    private Collection<Integer> getShardingValue(ComplexKeysShardingValue<Integer> shardingValues, final String key) {
        Collection<Integer> valueSet = new ArrayList<>();
        Map<String, Collection<Integer>> columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        if (columnNameAndShardingValuesMap.containsKey(key)) {
            valueSet.addAll(columnNameAndShardingValuesMap.get(key));
        }
        return valueSet;
    }

}
