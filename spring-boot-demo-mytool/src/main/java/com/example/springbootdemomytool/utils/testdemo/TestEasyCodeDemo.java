package com.example.springbootdemomytool.utils.testdemo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TestEasyCodeDemo
 * @Description
 * @Author H
 * @Date 2021/9/27 10:12
 * @Version 1.0
 */
public class TestEasyCodeDemo {
    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        test8();

    }

    public static void test8() {
        // 返回两个字段
        ImmutablePair<Integer, String> pair = ImmutablePair.of(1, "yideng");
        System.out.println(pair.getLeft() + "," + pair.getRight());

        // 返回三个字段
        ImmutableTriple<Integer, String, Date> triple = ImmutableTriple.of(1, "yideng", new Date());
        System.out.println(triple.getLeft() + "," + triple.getMiddle() + "," + triple.getRight());
    }

    public static void test7() throws ParseException {
        // Date 类型转String类型
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);

        // String 类型转Date类型
        // String类型转Date类型
        Date date1 = DateUtils.parseDate("2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date1);

        // 计算一个小时后的日期
        Date date2 = DateUtils.addHours(new Date(), 1);
        System.out.println(date2);
    }

    public static void test6() {
        String str = StringUtils.repeat("ab", 22);
        System.out.println(str);
    }

    public static void test5() {
        String str = "hello";
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 1.1 List集合拼接成以逗号分隔的字符串
     */
    public static void test1() {
        // 如何把list集合拼接成以逗号分隔的字符串 a b c
        List<String> list = Arrays.asList("a", "b", "c");
        // 第一种方法，可以用stream流
        String join = list.stream().collect(Collectors.joining(","));
        System.out.println(join);
        // 第二种方法，其实String也有join方法可以实现功能
        String join1 = String.join(",", list);
        System.out.println(join1);
    }

    /**
     * 1.2 比较两个字符串是否相等，忽略大小写
     */
    public static void test2() {
        if ("aaa".equalsIgnoreCase("aAA")) {
            System.out.println("相等");
        }
    }

    /**
     * 1.3 比较两个对象是否相等
     */
    public static void test3() {
        System.out.println(Objects.equals("A", "A"));
    }

    /**
     * 1.4 两个List集合取交集
     */
    public static void test4() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");
        list.retainAll(list2);
        System.out.println(list);
    }


}
