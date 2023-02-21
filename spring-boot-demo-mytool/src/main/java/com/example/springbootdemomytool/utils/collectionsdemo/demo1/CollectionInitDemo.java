package com.example.springbootdemomytool.utils.collectionsdemo.demo1;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName CollectionInitDemo
 * @Description Java 中初始化 List 集合的 8 种方式!
 * https://blog.csdn.net/qq_39101581/article/details/88393876
 * @Author H
 * @Date 2023/2/21 10:56
 * @Version 1.0
 */
public class CollectionInitDemo {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    /**
     * 1、常规方式
     */
    public static void test1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println("getList1:" + list);
    }

    /**
     * 2、Arrays 工具类
     */
    public static void test2() {
        // 生成的list不可变
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println("getList2:" + list);
        // 如果要可变需要用ArrayList包装一下
        List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3"));
        numbers.add("4");
        System.out.println("numbers: " + numbers);
    }

    /**
     * 3、Collections 工具类
     */
    public static void test3() {
        // 生成的list不可变
        List<String> list = Collections.nCopies(3, "1");
        System.out.println("getList3: " + list);
        // 如果要可变需要用ArrayList包装一下
        List<String> dogs = new ArrayList<>(Collections.nCopies(3, "dog"));
        dogs.add("dog");
        System.out.println("dogs: " + dogs);
    }

    /**
     * 4、Lists 工具类
     */
    public static void test4() {
        List<String> list = Lists.newArrayList("1", "2", "3");
        System.out.println("getList4: " + list);
    }

    /**
     * 5、匿名内部类
     */
    public static void test5() {
        List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        System.out.println("getList5: " + list);
    }

    /**
     * 6、ImmutableList
     */
    public static void test6() {
        List<String> list = ImmutableList.of("1", "2", "3");
        System.out.println("getList6: " + list);
    }

    /**
     * 7、Java8 Stream
     */
    public static void test7() {
        List<String> list = Stream.of("1", "2", "3").collect(Collectors.toList());
        System.out.println("getList7: " + list);
    }

    /**
     * 8、Java9 List.of
     */
//    public static void test8(){
//        List<String> list = List.of{"1","2","3"};
//        System.out.println("getList8: "+list);
//    }







}
