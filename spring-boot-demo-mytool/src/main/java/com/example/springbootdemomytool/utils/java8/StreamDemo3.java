package com.example.springbootdemomytool.utils.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName StreamDemo3
 * @Description https://mp.weixin.qq.com/s?__biz=MzU3MDAzNDg1MA==&mid=2247494949&idx=1&sn=06adf1947bbfbcf424b855628578092e&chksm=fcf732e8cb80bbfecb19326896c06db3c2ae9ba12f6f8bf681ec134782d689da1b8324708e69&scene=178&cur_album_id=2110812371806912514#rd
 * @Author H
 * @Date 2022/7/21 19:04
 * @Version 1.0
 */
public class StreamDemo3 {


    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "New York"));
        personList.add(new Person("Jack", 7000, 19, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 20, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 21, "female", "New York"));
        personList.add(new Person("Owen", 9500, 29, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 21, "female", "New York"));
        test1();

        test2();

        test3();

        List<String> filterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("工资高于8000的员工姓名: " + filterList);

        // 案例一：获取String集合中最长的元素。
        List<String> list = Arrays.asList("adsad", "dsadt", "ds", "dsadas", "dsarwqq");
        Optional<String> max00 = list.stream().max(Comparator.comparing(String::length));
        System.out.println("max length string: " + max00.get());

        // 案例二：获取Integer集合中的最大值。
        List<Integer> list1 = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max1 = list1.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list1.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序的最大值: " + max1.get());
        System.out.println("自定义排序的最大值: " + max2.get());

        // 案例三：获取员工工资最高的人。
        Optional<Person> max3 = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值: " + max3.get().getSalary());

        // 案例四：计算Integer集合中大于6的元素的个数。
        long count = list1.stream().filter(x -> x > 6).count();
        System.out.println(" list中大于6的元素个数: " + count);

        // 案例一：英文字符串数组的元素全部改为大写。整数数组每个元素+3。
        List<String> stringList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<Integer> integerList = list1.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素大写: " + stringList);
        System.out.println("每个元素+3：" + integerList);

        // 案例二：将员工的薪资全部增加1000。
        // 不改变原来员工集合的方式
        List<Person> personList1 = personList.stream().map(person -> {
            Person person1 = new Person(person.getName(), 0, 0, null, null);
            person1.setSalary(person.getSalary() + 10000);
            return person1;
        }).collect(Collectors.toList());
        System.out.println("一次改动前: " + personList.get(0).getName() + "--->" + personList.get(0).getSalary());
        System.out.println("一次改动后: " + personList1.get(0).getName() + "--->" + personList1.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personList2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前: " + personList2.get(0).getName() + "--->" + personList2.get(0).getSalary());
        System.out.println("二次改动后: " + personList2.get(0).getName() + "--->" + personList2.get(0).getSalary());


        // 案例三：将两个字符数组合并成一个新的字符数组。
        List<String> list2 = Arrays.asList("m,k,l,a", "1,3,5,7,");
        List<String> list3 = list2.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合: " + list2);
        System.out.println("处理后的集合: " + list3);

        // 案例一：求Integer集合的元素之和、乘积和最大值。
        // 求和方式1
        Optional<Integer> reduceSum1 = list1.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> reduceSum2 = list1.stream().reduce(Integer::sum);
        //求和方式3
        Integer reduceSum3 = list1.stream().reduce(0, Integer::sum);
        // 求乘积
        Optional<Integer> reduce = list1.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> max4 = list1.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值方式2
        Integer max5 = list1.stream().reduce(1, Integer::max);

        // 案例二：求所有员工的工资之和和最高工资。
        // 求工资之和方式1：
        Optional<Integer> sumSalary1 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), (sum1, sum2) -> sum1 + sum2);
        // 球工资之和方式3
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方式1
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        // 求最高工资方式2
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), (max111, max222) -> max111 > max222 ? max111 : max222);
        System.out.println("工资之和: " + sumSalary1.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资: " + maxSalary + "," + maxSalary2);

        // 求总和
        Long count1 = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> maxSalary1 = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        // 求工资之和
        IntSummaryStatistics sumSalary111 = personList.stream().collect(Collectors.summarizingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数: " + count1);
        System.out.println("员工平均工资: " + average);
        System.out.println("员工工资总和: " + sumSalary111);
        System.out.println("员工工资所有统计: " + collect);
        System.out.println("员工最高工资: " + maxSalary1.get());

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况: " + part);
        System.out.println("员工按性别分组情况: " + group);
        System.out.println("员工按性别、地区: " + group2);

        // 接合
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名: " + names);
        List<String> list4 = Arrays.asList("A", "B", "C");
        String collect1 = list4.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串: " + collect1);

        // 归约
        // 每个员工减去起征点后的薪资之和
        Integer collect2 = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和: " + collect2);
        Optional<Integer> reduce1 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和: " + reduce1.get());

        // 排序
        // 按工资升序排序
        List<String> newList1 = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> collect3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge))
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> collect4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序: " + newList1);
        System.out.println("按工资降序排序: " + newList2);
        System.out.println("先按工资再按年龄升序排序: " + collect3);
        System.out.println("先按工资再按年龄自定义降序排序: " + collect4);

        // 提取、组合
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};
        Stream<String> arr11 = Stream.of(arr1);
        Stream<String> arr21 = Stream.of(arr2);
        // concat: 合并两个流 distinct: 去重
        List<String> newList5 = Stream.concat(arr11, arr21).distinct().collect(Collectors.toList());
        // limit: 限制从流中获得前n个数据
        List<Integer> collect5 = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip: 跳过前n个数据
        List<Integer> collect6 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并: " + newList5);
        System.out.println("limit: " + collect5);
        System.out.println("skip: " + collect6);

    }

    public static void test3() {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9, 11);

        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);
    }

    public static void test2() {
        List<Integer> list = Arrays.asList(6, 7, 3, 2, 9, 8, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意(适用于并行流)
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个: " + findFirst.get());
        System.out.println("匹配任意: " + findAny.get());
        System.out.println("是否包含符合大于6的值: " + anyMatch);
    }

    public static void test1() {

        List<String> list = Arrays.asList("a", "b", "c");
        // 创建一个顺讯
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> stringStream = list.parallelStream();

        // 用数组创建流
        int[] array = {1, 3, 5, 6, 8};
        IntStream stream1 = Arrays.stream(array);

        // 静态方法
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(1, (x) -> x + 4).limit(4);
        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
    }


}
