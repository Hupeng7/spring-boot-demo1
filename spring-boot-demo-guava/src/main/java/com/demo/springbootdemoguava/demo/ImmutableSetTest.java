package com.demo.springbootdemoguava.demo;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ImmutableSetTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/7/16 11:47
 * @Version 1.0
 */
@Slf4j
public class ImmutableSetTest {

    public static void main(String[] args) {
        Set<String> immutableSet = ImmutableSet.of("a", "b", "c");
        log.info("immutableSet : {}", immutableSet);
        Set<String> set = new HashSet<String>() {
            {
                add("1");
                add("2");
                add("3");
            }
        };
        set.add("4");
        // 不可变对象不能含有null
        set.add(null);
        log.info("set : {}", set);

        Set<Integer> builderSet = ImmutableSet.<Integer>builder().add(1).add(2).add(3).build();
        log.info("builderSet : {}", builderSet);

        Set<String> iSet = ImmutableSet.copyOf(set);
        log.info("iSet : {}", iSet);


    }


}
