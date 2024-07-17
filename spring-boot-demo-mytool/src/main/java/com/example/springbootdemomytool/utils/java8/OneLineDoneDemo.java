package com.example.springbootdemomytool.utils.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;


/**
 * @ClassName OneLineDoneDemo
 * @Description 10 个牛逼的单行代码编程技巧，本文列举了十个使用一行代码即可独立完成(不依赖其他代码)的业务逻辑，
 * 主要依赖的是Java8中的Lambda和Stream等新特性以及try-with-resources、JAXB等。
 * https://mp.weixin.qq.com/s/BpY1jm0fro8m1TukuJQEXg
 * @Author hup
 * @Date 2020/7/29 11:47
 * @Version 1.0
 */
public class OneLineDoneDemo {

    public static void main(String[] args) {
//        skill1();
//        skill2();
//        skill3();
//
//        skill5();
//        skill8();
//        skill4();
        List<UserQ> objectByTxt = getObjectByTxt("E:\\temp\\data.txt");
        System.out.println(objectByTxt);
    }

    public static void skill1() {
        // 1、对列表/数组中的每个元素都乘以2
        int[] ints = range(1, 10).map(i -> i * 2).toArray();
        System.out.println("ints:" + Arrays.toString(ints));
        List<Integer> result = range(1, 10).map(i -> i * 2).boxed().collect(Collectors.toList());
        System.out.println("result:" + result);
    }

    public static void skill2() {
        // 计算集合、数组中的数字之和
        int sum1 = range(1, 1000).sum();
        System.out.println("sum1:" + sum1);
        int sum2 = range(1, 1000).reduce(0, Integer::sum);
        System.out.println("sum2:" + sum2);
        Integer sum3 = Stream.iterate(0, i -> i + 1).limit(1000).reduce(0, Integer::sum);
        System.out.println("sum3:" + sum3);
        int sum4 = IntStream.iterate(0, i -> i + 1).limit(1000).reduce(0, Integer::sum);
        System.out.println("sum4:" + sum4);

    }

    public static void skill3() {
        final List<String> keywords = Arrays.asList("brown", "fox", "dog", "pangram");
        final String tweet = "The quick brown fox jumps over a lazy dog.#pangram http://www.rinkworks.com/words/pangrams.shtml";

        boolean b1 = keywords.stream().anyMatch(tweet::contains);
        System.out.println("b1:" + b1);
        Boolean b2 = keywords.stream().reduce(false, (b, keyword) -> b || tweet.contains(keyword), (l, r) -> l || r);
        System.out.println("b2:" + b2);
    }

    @Data
    @AllArgsConstructor
    static class UserQ {
        private Long id;
        private String name;
        private Integer status;
    }

    public static void skill4() {
        // 读取文件内容
        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\temp\\data.txt"))) {
            String fileText = reader.lines().reduce("", String::concat);
            System.out.println(fileText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\temp\\data.txt"))) {
            List<String> fileLines = reader.lines().collect(Collectors.toCollection(LinkedList<String>::new));
            System.out.println(fileLines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(new File("E:\\temp\\data.txt").toPath(), Charset.defaultCharset())) {
            List<String> fileLines = lines.collect(Collectors.toCollection(LinkedList<String>::new));
            System.out.println(fileLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<UserQ> getObjectByTxt(String fileName) {
        List<UserQ> list = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(new FileInputStream(fileName));
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            String len;
            int batchCount = 10;
            while ((len = bufferedReader.readLine()) != null) {
                String[] split = len.split(",");
                UserQ userQ = new UserQ(
                        Long.parseLong(split[0]),
                        split[1],
                        Integer.parseInt(split[2])
                );
                list.add(userQ);
                // 达到500条就异步写入一下
            }
        } catch (IOException e) {

        }
        return list;
    }

    public static void skill5() {
        // 输出歌曲《Happy Birthday to You!》 根据集合中不同的元素输出不同的字符串
        range(1, 5).boxed().map(i -> {
            System.out.print("Happy Birthday ");
            if (i == 3) {
                return "dear Name";
            } else {
                return "to You";
            }
        }).forEach(System.out::println);
    }

    public static void skill6() {
        // 过滤并分组集合中的数字
        // Map<String, List<Integer>> result1 = Stream.of(49, 58, 76, 82, 88, 90).collect(groupingBy(forPredicate(i -> i > 60, "passed", "failed")));

    }

    public static void skill7() {
        // 获取并解析xml协议的Web Service
//        FeedType feed = JAXB.unmarshal(new URL("http://search.twitter.com/search.atom?&q=java8"), FeedType.class);
//        JAXB.marshal(feed, System.out);
    }

    public static void skill8() {
        // 获取集合中最小、最大的数字
        int min = Stream.of(14, 35, -7, 46, 98).reduce(Integer::min).get();
        System.out.println("min:" + min);
        min = Stream.of(14, 35, -7, 46, 98).min(Integer::compare).get();
        System.out.println("min:" + min);
        // min = Stream.of(14, 35, -7, 46, 98).mapToInt(Integer::new).min();

        int max = Stream.of(14, 35, -7, 46, 98).reduce(Integer::max).get();
        System.out.println("max:" + max);
        max = Stream.of(14, 35, -7, 46, 98).max(Integer::compare).get();
        System.out.println("max:" + max);
        // max = Stream.of(14, 35, -7, 46, 98).mapToInt(Integer::new).max();
    }

    public static void skill9() {
        // 并行处理
        List<Integer> dataList = new ArrayList<>();
        // long result = dataList.parallelStream().mapToInt(line -> processItem(line)).sum();
    }

    public static void skill10() {

    }


}
