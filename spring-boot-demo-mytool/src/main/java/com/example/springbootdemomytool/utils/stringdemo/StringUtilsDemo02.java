package com.example.springbootdemomytool.utils.stringdemo;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringUtilsDemo02
 * @Description https://mp.weixin.qq.com/s/JEl4NmDXET94ZFBPJjYX7w
 * @Author H
 * @Date 2021/8/9 14:27
 * @Version 1.0
 */
public class StringUtilsDemo02 {

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
    }

    public static void test1() {
        boolean test1 = StringUtils.isEmpty(null);
        System.out.println("test1: " + test1);  // true

        boolean test2 = StringUtils.isEmpty("");
        System.out.println("test2: " + test2);  // true

        boolean test3 = StringUtils.isEmpty(" ");
        System.out.println("test3: " + test3);  // false

        boolean test4 = StringUtils.isEmpty("bob");
        System.out.println("test4: " + test4); // false

        boolean test5 = StringUtils.isEmpty(" bob ");
        System.out.println("test5: " + test5); // false
    }

    public static void test2() {
        boolean anyEmpty1 = StringUtils.isAnyEmpty(null);
        System.out.println("anyEmpty1: " + anyEmpty1); // false

        boolean anyEmpty11 = StringUtils.isAnyEmpty("");
        System.out.println("anyEmpty11: " + anyEmpty11); // true

        boolean anyEmpty2 = StringUtils.isAnyEmpty(null, "foo");
        System.out.println("anyEmpty2: " + anyEmpty2); // true

        boolean anyEmpty3 = StringUtils.isAnyEmpty("", "bar");
        System.out.println("anyEmpty3: " + anyEmpty3); // true

        boolean anyEmpty4 = StringUtils.isAnyEmpty("bob", "");
        System.out.println("anyEmpty4: " + anyEmpty4); // true

        boolean anyEmpty5 = StringUtils.isAnyEmpty(" bob ", null);
        System.out.println("anyEmpty5: " + anyEmpty5); // true

        boolean anyEmpty6 = StringUtils.isAnyEmpty(" ", "bar");
        System.out.println("anyEmpty6: " + anyEmpty6); // false

        boolean anyEmpty7 = StringUtils.isAnyEmpty("foo", "bar");
        System.out.println("anyEmpty7: " + anyEmpty7); // false
    }

    public static void test3() {
        boolean noneEmpty1 = StringUtils.isNoneEmpty(null);
        System.out.println("noneEmpty1: " + noneEmpty1); // true

        boolean noneEmpty2 = StringUtils.isNoneEmpty(null, "foo");
        System.out.println("noneEmpty2: " + noneEmpty2); // false

        boolean noneEmpty3 = StringUtils.isNoneEmpty("", "bar");
        System.out.println("noneEmpty3: " + noneEmpty3); // false

        boolean noneEmpty4 = StringUtils.isNoneEmpty("boo", "");
        System.out.println("noneEmpty4: " + noneEmpty4); // false

        boolean noneEmpty5 = StringUtils.isNoneEmpty(" boo ", null);
        System.out.println("noneEmpty5: " + noneEmpty5); // false

        boolean noneEmpty6 = StringUtils.isNoneEmpty(" ", "bar");
        System.out.println("noneEmpty6: " + noneEmpty6); // true

        boolean noneEmpty7 = StringUtils.isNoneEmpty("foo", "bar");
        System.out.println("noneEmpty7: " + noneEmpty7); // true
    }

    public static void test4() {
        boolean blank1 = StringUtils.isBlank(null);
        System.out.println("blank1: " + blank1); // true

        boolean blank2 = StringUtils.isBlank("");
        System.out.println("blank2: " + blank2); // true

        boolean blank3 = StringUtils.isBlank(" ");
        System.out.println("blank3: " + blank3); // true

        boolean blank4 = StringUtils.isBlank("bob");
        System.out.println("blank4: " + blank4); // false

        boolean blank5 = StringUtils.isBlank(" bob ");
        System.out.println("blank5: " + blank5); // false
    }

    public static void test5() {
        boolean anyBlank1 = StringUtils.isAnyBlank(null);
        System.out.println("anyBlank1: " + anyBlank1); // false

        boolean anyBlank2 = StringUtils.isAnyBlank(null, "foo");
        System.out.println("anyBlank2: " + anyBlank2); // true

        boolean anyBlank3 = StringUtils.isAnyBlank(null, null);
        System.out.println("anyBlank3: " + anyBlank3); // true

        boolean anyBlank4 = StringUtils.isAnyBlank("", "bar");
        System.out.println("anyBlank4: " + anyBlank4); // true

        boolean anyBlank5 = StringUtils.isAnyBlank("bob", "");
        System.out.println("anyBlank5: " + anyBlank5); // true

        boolean anyBlank6 = StringUtils.isAnyBlank(" bob ", null);
        System.out.println("anyBlank6: " + anyBlank6); // true

        boolean anyBlank7 = StringUtils.isAnyBlank(" ", "bar");
        System.out.println("anyBlank7: " + anyBlank7); // true

        boolean anyBlank8 = StringUtils.isAnyBlank("foo", "bar");
        System.out.println("anyBlank8: " + anyBlank8); // false

    }

    public static void test6() {
        boolean noneBlank1 = StringUtils.isNoneBlank(null);
        System.out.println("noneBlank1: " + noneBlank1); // true

        boolean noneBlank2 = StringUtils.isNoneBlank(null, "foo");
        System.out.println("noneBlank2: " + noneBlank2); // false

        boolean noneBlank3 = StringUtils.isNoneBlank(null, null);
        System.out.println("noneBlank3: " + noneBlank3); // false

        boolean noneBlank4 = StringUtils.isNoneBlank("", "bar");
        System.out.println("noneBlank4: " + noneBlank4); // false

        boolean noneBlank5 = StringUtils.isNoneBlank("bob", "");
        System.out.println("noneBlank5: " + noneBlank5); // false

        boolean noneBlank6 = StringUtils.isNoneBlank(" bob ", null);
        System.out.println("noneBlank6: " + noneBlank6); // false

        boolean noneBlank7 = StringUtils.isNoneBlank(" ", "bar");
        System.out.println("noneBlank7: " + noneBlank7); // false

        boolean noneBlank8 = StringUtils.isNoneBlank("foo", "bar");
        System.out.println("noneBlank8: " + noneBlank8); // true
    }


}
