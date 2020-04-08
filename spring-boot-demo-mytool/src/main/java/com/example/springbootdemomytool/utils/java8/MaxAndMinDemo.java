package com.example.springbootdemomytool.utils.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName StreamDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/7 15:40
 * @Version 1.0
 */
public class MaxAndMinDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        System.out.println("==================Stream方法==================");
        maxByStream(list);
        minByStream(list);

        //什么时候会执行orElse()呢？ 当数组为空的时候，就会执行。
        System.out.println("==================空数组==================");
        List<Integer> listEmpty = Arrays.asList();
        maxByStream(listEmpty);
        minByStream(listEmpty);

        System.out.println("==================Collections类方法==================");
        maxByCollections(list);
        minByCollections(list);
    }

    public static Object maxByStream(List list) {
        System.out.println("\nstream()方法获取最大值");
        Object max = list.stream().filter(e -> e != null).max(Comparator.naturalOrder()).orElse(null);
        System.out.println("max=" + max);
        return max;
    }

    public static Object minByStream(List list) {
        System.out.println("\nstream()方法获取最小值");
        Object min = list.stream().filter(e -> e != null).min(Comparator.naturalOrder()).orElse(null);
        System.out.println("min=" + min);
        return min;
    }

    public static Object maxByCollections(List list) {
        Object max = null;
        if (list.size() > 0) {
            max = Collections.max(list);
        }
        System.out.println("\n使用Collections类方法来取得最大值为：" + max);
        return max;
    }

    public static Object minByCollections(List list) {
        Object min = null;
        if (list.size() > 0) {
            min = Collections.min(list);
        }
        System.out.println("\n使用Collections类方法来取得最小值为：" + min);
        return min;
    }


}
