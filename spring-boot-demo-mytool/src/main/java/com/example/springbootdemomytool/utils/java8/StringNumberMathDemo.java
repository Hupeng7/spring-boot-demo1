package com.example.springbootdemomytool.utils.java8;

import org.junit.Test;

import java.util.stream.Collectors;

/**
 * @ClassName StringNumberMathDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/15 19:03
 * @Version 1.0
 */
public class StringNumberMathDemo {

    /**
     * 第一个方法使用指定的分隔符，将任何数量的字符串连接为一个字符串。
     */
    @Test
    public void testString() {
        String s1 = String.join(":", "foobar", "foo", "bar");
        System.out.println("s1: " + s1);
    }

    @Test
    public void testString2() {
        String s2 = "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        System.out.println("s2: " + s2); // s2: :abfor
    }


}
