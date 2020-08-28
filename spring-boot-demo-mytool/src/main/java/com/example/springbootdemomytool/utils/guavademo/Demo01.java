package com.example.springbootdemomytool.utils.guavademo;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Demo01
 * @Description
 * @Author hup
 * @Date 2020/8/25 10:25
 * @Version 1.0
 */
public class Demo01 {
    public static void main(String[] args) {
        // 不可变列表
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");
        System.out.println(list.get(2));
        // 不可变Map
        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "一", 2, "二", 3, "三");
        System.out.println(map.get(2));
        // 多值Map
        Multimap<String, String> phoneBook = ArrayListMultimap.create();
        phoneBook.put("zhangsan", "110");
        phoneBook.put("zhangsan", "119");
        System.out.println(phoneBook.get("zhangsan"));

        // Table表
        Table<Double, Double, String> g = HashBasedTable.create();
        g.put(31.23, 121.48, "人民广场");
        String target = g.get(31.23, 121.48);
        System.out.println(target);

        // Table，完全类似于数据库表
        Table<Integer, Integer, String> user = HashBasedTable.create();
        user.put(1, 1, "shangsan");
        user.put(1, 2, "lisi");
        String u11 = user.get(1, 1);
        System.out.println(u11);
        System.out.println(user.get(1, 2));
        System.out.println(user.get(1, 3)); // null

        // 集合工具类
        Map<String, Integer> user1 = new HashMap<>();
        user1.put("zhangsan", 24);
        user1.put("lisi", 25);
        user1.put("wangwu", 26);
        Map<String, Integer> filterMap = Maps.filterValues(user1,
                new Predicate<Integer>() {
                    @Override
                    public boolean apply(@Nullable Integer _age) {
                        return _age > 24;
                    }
                });
        System.out.println(filterMap.values());

        // 字符串操作
        // 定义连接符号
        Joiner joiner = Joiner.on(", ");
        // 可以连接多个对象，不局限于String;如果有null,则跳过
        String str = joiner.skipNulls().join("嘿", "Guava nice。");
        System.out.println(str);

        Map<String, String> map1 = new HashMap<>();
        map1.put("zhangsan", "normal employee");
        map1.put("lisi", "leader");
        // 键值之间以 "是" 连接，多个键值以空格分隔
        System.out.println(Joiner.on("\r\n").withKeyValueSeparator(" is ").join(map1));

        String str1 = "hello,Guava";
        // 以", "分隔
        for (String s : Splitter.on(",").split(str1)) {
            System.out.println(s);
        }

        /**
         * 按照固定长度分隔
         * 注意fixedLength方法，它是按照给定长度进行拆分的，比如在进行格式化打印的时候，
         * 一行最大可以打印120个字符，用此方法就很简单了
         */
        System.out.println("按照固定长度分隔");
        for (String s : Splitter.fixedLength(2).split(str1)) {
            System.out.println(s);
        }

        // 基本类型工具
        int[] ints = {10, 9, 20, 40, 80};
        // 从数组中取出最大值
        System.out.println(Ints.max(ints));

        List<Integer> integers = new ArrayList<>();
        // 把包装类型的集合转为基本类型数值
        ints = Ints.toArray(integers);
    }

}
