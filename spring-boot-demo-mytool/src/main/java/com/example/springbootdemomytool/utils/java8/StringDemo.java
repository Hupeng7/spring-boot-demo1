package com.example.springbootdemomytool.utils.java8;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StringDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/7/7 17:49
 * @Version 1.0
 */
public class StringDemo {

    public static void main(String[] args) {
        String a = "1234567890";
        // System.out.println(a.substring(0, 100));

        System.out.println("处理字符串join-------------");
        String join = String.join(":", "foobar", "foo", "bar");
        System.out.printf("join: %s", join);

        System.out.println();
        System.out.println("处理字符串chars---------------");
        String chars = "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        System.out.printf("chars: %s", chars);

        System.out.println();
        System.out.println("正则处理-------------------");
        String pattern1 = Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"));
        System.out.printf("pattern1: %s", pattern1);

        System.out.println();
        System.out.println("正则处理2-------------------");
        Pattern pattern2 = Pattern.compile(".*@gmail\\.com");
        long count = Stream.of("bob@gmail.com", "alice#hotmail.com")
                .filter(pattern2.asPredicate())
                .count();
        System.out.println("count: " + count);

    }


}
