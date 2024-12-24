package com.example.springbootdemomytool.utils.collectionsdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName Test01
 * @Description
 * @Author H
 * @Date 2024/11/18 11:17
 * @Version 1.0
 */
public class Test01 {
    public static void main(String[] args) {
        // 创建 List<Object[]> 示例数据
        List<Object[]> total = new ArrayList<>();

        // 向 total 列表添加一些数据
//        for (int i = 0; i < 2; i++) {
//            Object[] arr = new Object[10];
//            arr[7] = 10 + i; // weight
//            arr[8] = System.currentTimeMillis() - (i * 1000L); // createTime, 模拟递减的时间戳
//            total.add(arr);
//        }
        total.add(new Object[]{1,2,3,4,5,6,701,801,901});
        total.add(new Object[]{2,2,3,4,5,6,701,803,901});
        total.add(new Object[]{3,2,3,4,5,6,701,802,901});
        total.add(new Object[]{4,2,3,4,5,6,701,803,904});

        for (Object[] arr : total) {
            System.out.println("Weight: " + arr[7] + ", CreateTime: " + arr[8]);
        }

        // 使用 Stream API 对 total 列表按 weight 和 createTime 进行倒序排序
        List<Object[]> sortedList = total.stream()
                .sorted((a, b) -> {
                    // 先按 weight 倒序排序
                    Comparable weightA = (Comparable) a[7];
                    Comparable weightB = (Comparable) b[7];
                    int result = weightB.compareTo(weightA);
                    if (result != 0) {
                        return result;
                    }

                    // 如果 weight 相同，再按 createTime 倒序排序
                    Comparable timeA = (Comparable) a[8];
                    Comparable timeB = (Comparable) b[8];
                    return timeB.compareTo(timeA);
                })
                .collect(Collectors.toList());

        // 打印排序后的结果
        System.out.println("排序后的元素：");
        for (Object[] arr : sortedList) {
            System.out.println("Weight: " + arr[7] + ", CreateTime: " + arr[8]);
        }
    }
}
