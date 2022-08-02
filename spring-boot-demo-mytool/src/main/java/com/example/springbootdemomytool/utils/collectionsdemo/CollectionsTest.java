package com.example.springbootdemomytool.utils.collectionsdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollectionsTest
 * @Description
 * @Author H
 * @Date 2022/2/23 10:06
 * @Version 1.0
 */
public class CollectionsTest {

    public static void main(String[] args) {
        containsTest();

    }

    public static void containsTest() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        List<String> list1 = new ArrayList<>();
        list1.add("B");
        list1.add("D");
        List<String> list2 = new ArrayList<>();
        // true
        System.out.println(list.contains("A"));
        // true
        System.out.println(list.containsAll(list1));
        // false
        list1.add("E");
        System.out.println(list.containsAll(list1));
        // true 空集合也是true
        System.out.println(list.containsAll(list2));


    }
}
