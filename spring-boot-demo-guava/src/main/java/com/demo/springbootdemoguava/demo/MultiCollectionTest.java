package com.demo.springbootdemoguava.demo;

import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @ClassName MultiCollectionTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 12:06
 * @Version 1.0
 */
@Slf4j
public class MultiCollectionTest {
    public static void main(String[] args) {
        multiset();
        multiMap();
        biMap();
    }

    /**
     * 特点：双向映射，可以翻转
     */
    private static void biMap() {
        log.info("HashBiMap.crate() k,v可以翻转的Map,值不可重复------");
        HashBiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("a", 1);
        biMap.put("b", 2);
        biMap.put("c", 3);

        log.info("biMap : {}", biMap);
        Map<Integer, String> inverseMap = biMap.inverse();
        log.info("inverseMap : {}", inverseMap.toString());
        // 同一个kv不同的展示方式而已，修改原map还是inverseMap都可以修改原有的值
        biMap.put("d", 4);
        inverseMap.put(5, "e");
        log.info("biMap : {}", biMap);
        log.info("inverseMap : {}", inverseMap);

    }

    /**
     * 特点：允许一键多值Map
     */
    private static void multiMap() {
        log.info("HashMultimap.create() 值行为类似HashSet --------------");
        SetMultimap<String, Integer> valueSimilarHashSet = HashMultimap.create();
        valueSimilarHashSet.put("a", 1);
        // 重复的值不会产生多个
        valueSimilarHashSet.put("a", 1);
        valueSimilarHashSet.put("b", 2);
        valueSimilarHashSet.put("b", null);
        log.info("valueSimilarHashSet : {}", valueSimilarHashSet.toString());
        log.info("a : {}", valueSimilarHashSet.get("a"));
        log.info("c : {}", valueSimilarHashSet.get("c"));

        log.info("ArrayListMultimap.create() 值行为类似 ArrayList -------");
        ListMultimap<String, Integer> valueSimilarArrayList = ArrayListMultimap.create();
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("a", 1);
        valueSimilarArrayList.put("b", 2);
        valueSimilarArrayList.put("b", 3);
        valueSimilarArrayList.put("b", 4);
        valueSimilarArrayList.put("c", 10);
        // 允许null
        valueSimilarArrayList.put("c", null);
        log.info("valueSimilarArrayList : {}", valueSimilarArrayList.toString());
        // 不会返回null,最起码都是个空集合
        log.info("d : {}", valueSimilarArrayList.get("d"));
    }

    /**
     * 特点：可计数的Set
     */
    private static void multiset() {
        log.info("HashMultiset.create() 和 Set很像，但是可以统计多少重复的-------");
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("aaa");
        multiset.add("aaa");
        multiset.add("aaa");
        multiset.add("bbb");
        multiset.add("bbb");
        multiset.add("ccc");
        log.info("aaa count : " + multiset.count("aaa"));
        log.info("total size : " + multiset.size());
        // 转到set接口 elementSet()
        log.info("set size : " + multiset.elementSet().size());
    }


}
