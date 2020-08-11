package com.demo.springbootdemoguava.demo;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @ClassName StringsTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 17:30
 * @Version 1.0
 */
@Slf4j
public class StringsTest {
    public static void main(String[] args) {
        // JDKString自带的split方法有问题  末尾的  都被忽略了
        String a = " ,a,b,,";
        log.info(Arrays.toString(a.split(",")));

        // Joiner 和 Splitter都可以通过Stream方法替代
        Joiner joiner = Joiner.on(";").skipNulls();
        String joinStr = joiner.join("a", "b", null, "", "c");
        log.info("joinStr : {}", joinStr);

        Iterable iterableStr = Splitter.on(",").trimResults().omitEmptyStrings().split("  a,b,, c d,e ,");
        log.info(iterableStr.toString());

        // 字符匹配器
        System.out.println("---------------------------1a b 2 c 3----------------------------------");
        System.out.println("retainFrom 'a','z' :" + CharMatcher.inRange('a', 'z').retainFrom("  1a b 2 c  3 "));
        System.out.println("retainFrom '0','9' :" + CharMatcher.inRange('0', '9').retainFrom("  1a b 2 c  3 "));
        System.out.println("removeFrom 'a','z' :" + CharMatcher.inRange('a', 'z').removeFrom("  1a b 2 c  3 "));
        System.out.println("removeFrom '0','9' :" + CharMatcher.inRange('0', '9').removeFrom("  1a b 2 c  3 "));
        System.out.println("replaceFrom 'a','z' :" + CharMatcher.inRange('a', 'z').replaceFrom("  1a b 2 c  3 ", "f"));
        System.out.println("replaceFrom '0','9' :" + CharMatcher.inRange('0', '9').replaceFrom("  1a b 2 c  3 ", "f"));

        // 字符集
        byte[] bytes = "Charsets.UTF_8".getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        // 某些格式转换，一些特别的格式转换，比如驼峰，全大写下划线等，写代码生成器的时候用的比较多
        String lowerCamel = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "SimpleClassName");
        System.out.println(lowerCamel);
    }


}
