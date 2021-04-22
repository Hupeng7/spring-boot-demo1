package com.example.springbootdemomytool.utils.collectionsdemo;

import com.example.springbootdemomytool.beans.User;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName CollectionsDemo
 * @Description
 * @Author hup
 * @Date 2020/8/25 14:24
 * @Version 1.0
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        // 明确键类型的Map
        BigDecimal bigDecimal = BigDecimal.valueOf(0.11D);

        // 判断集合是否为空
        boolean empty = CollectionUtils.isEmpty(list);

        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list2.add(i + 1);
        }

        List<List<Integer>> lists = fixedGrouping3(list2, 10);
        System.out.println(lists);


    }

    // 赋值静态成员变量正例
    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
    }

    private static List<String> list = new ArrayList<>();

    static {
        list.add("Tom");
        list.add("Jerry");
        list.add("Anthony");
    }

    // 返回空数组和空集正例
    public static User[] getUsers() {
        return new User[0];
    }

    public static List<User> getUserList() {
        return Collections.emptyList();
    }

    public static Map<String, User> getUserMap() {
        return Collections.emptyMap();
    }

    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0) {
            return null;
        }

        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }


    /**
     * https://blog.csdn.net/csdnfeiguo/article/details/98043173
     * 一个List分成多个List
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping3(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0) {
            return null;
        }

        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }



}
