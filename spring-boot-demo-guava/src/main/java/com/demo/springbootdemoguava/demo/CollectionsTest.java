package com.demo.springbootdemoguava.demo;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @ClassName CollectionsTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 13:57
 * @Version 1.0
 */
@Slf4j
public class CollectionsTest {

    public static void main(String[] args) {
        lists();
        sets();
        maps();
        multiMaps();
    }

    private static void multiMaps() {
        log.info("multiMaps---------------");
        Set<String> set = ImmutableSet.of("a", "b", "ab", "abc", "add");
        log.info(Multimaps.index(set, String::length).toString());

        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.putAll("a", Ints.asList(1, 2, 3));
        arrayListMultimap.putAll("b", Ints.asList(4, 5, 6));
        arrayListMultimap.putAll("a", Ints.asList(5, 6, 7));

        TreeMultimap<Integer, String> treeMultimap = Multimaps.invertFrom(arrayListMultimap, TreeMultimap.create());
        log.info(treeMultimap.toString());
    }

    private static void maps() {
        log.info("Maps---------------------");
        Maps.newHashMap();

        // 分组
        Map<Integer, String> uniqueIndexMap = Maps.uniqueIndex(ImmutableList.copyOf(new String[]{"a", "ab", "abc"}), String::length);
        log.info("uniqueIndexMap : {}", uniqueIndexMap.toString());
        Map<String, Integer> map1 = ImmutableMap.of("a", 1, "b", 2, "c", 3, "e", 5);
        Map<String, Integer> map2 = ImmutableMap.of("a", 1, "b", 2, "c", 4, "d", 4);

        //2个map进行比较
        MapDifference<String, Integer> mapDifference = Maps.difference(map1, map2);
        log.info("entriesInCommon : " + mapDifference.entriesInCommon());
        log.info("entriesDiffering : " + mapDifference.entriesDiffering());
        log.info("entriesOnlyOnLeft : " + mapDifference.entriesOnlyOnLeft());
        log.info("entriesOnlyOnRight : " + mapDifference.entriesOnlyOnRight());
    }

    private static void sets() {
        log.info("Sets----------------------");
        // 和Lists一样的构造方法
        Sets.newHashSet("", "");
        // 偶尔用 比较引用
        Sets.newIdentityHashSet();

        Set<String> a = ImmutableSet.of("a", "b", "c", "d");
        Set<String> b = ImmutableSet.of("a", "b", "e", "f");
        TreeSet<String> treeSet = new TreeSet<>(Ordering.natural());

        // 方便的对set进行比较
        log.info("union : " + Sets.union(a, b));
        log.info("intersection : " + Sets.intersection(a, b));
        log.info("difference : " + Sets.difference(a, b));
        log.info("symmetricDifference : " + Sets.symmetricDifference(a, b));

    }

    private static void lists() {
        log.info("Lists-------------------");
        // 一般没有参数的情况 不建议用这个方法
        Lists.newArrayList();

        // 用的较多
        ArrayList<Integer> lists = Lists.newArrayList(1, 2, 3, 4);
        // 反转
        log.info(Lists.reverse(lists).toString());
        // 指定初始化大小的list
        Lists.newArrayListWithCapacity(3);

        // 快速完成到集合的转换
        List<Integer> list = Ints.asList(1, 3, 5, 6, 9);
        log.info(Ints.join(",", 1, 3, 1, 4));

        // 原生类型数组快速合并
        int[] newIntArray = Ints.concat(new int[]{1, 2}, new int[]{2, 3, 4});
        log.info("new int array length: " + newIntArray.length);

        // 最大、最小
        log.info("max: " + Ints.max(newIntArray) + ", min: " + Ints.min(newIntArray));

        // 是否包含
        boolean contains = Ints.contains(newIntArray, 6);
        log.info("contains: " + contains);

        // 集合到数组的转换
        int[] array = Ints.toArray(list);


    }


}
