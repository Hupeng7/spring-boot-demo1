package com.example.springbootdemomytool.utils.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegularExpressionDemo1
 * @Description 常用正则表达式最强整理
 * https://mp.weixin.qq.com/s/9hH3BBBAMjwWs3XTqAU_cg
 * @Author H
 * @Date 2021/4/22 13:47
 * @Version 1.0
 */
public class RegularExpressionDemo1 {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test6();
    }

    private static void test1() {
        String content = "hello,test one.";
        String pattern = ".*test.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串content是否包含了pattern字符串?" + isMatch);
    }

    private static void test2() {
        // 按指定模式在字符串查找
        String line = "This orfer was placed for QT3000!OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建Pattern对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建matcher对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("No Match");
        }
    }

    private static void test3() {
        String input = "-331.11";
        // 校验数字的表达式
        String r1 = "^[0-9]*$";
        boolean matches1 = Pattern.matches(r1, input);
        System.out.println(input + "是数字: " + matches1);

        String r2 = "^\\d{2}$";
        boolean matches2 = Pattern.matches(r2, input);
        System.out.println(input + "是n位的数字：" + matches2);

        String r3 = "^\\d{1,3}$";
        boolean matches3 = Pattern.matches(r3, input);
        System.out.println(input + "是m-n位的数字：" + matches3);

        String r4 = "^\\d{2,}$";
        boolean matches4 = Pattern.matches(r4, input);
        System.out.println(input + "是至少n位的数字: " + matches4);

        String r5 = "^(0|[1-9][0-9]*)$";
        boolean matches5 = Pattern.matches(r5, input);
        System.out.println(input + "是零和非零开头的数字: " + matches5);

        String r6 = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
        boolean matches6 = Pattern.matches(r6, input);
        System.out.println(input + "是非零开头的最多带两位小数的数字" + matches6);

        String r7 = "^(\\-)?\\d+(\\.\\d{1,2})$";
        boolean matches7 = Pattern.matches(r7, input);
        System.out.println(input + "是带1-2位小数的正数或负数" + matches7);

        String r8 = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
        boolean matches8 = Pattern.matches(r8, input);
        System.out.println(input + "是正数、负数、和小数" + matches8);

        String r9 = "^[0-9]+(.[0-9]{2})?$";
        boolean matches9 = Pattern.matches(r9, input);
        System.out.println(input + "是有两位小数的正实数" + matches9);

        String r10 = "^[0-9]+(.[0-9]{1,3})?$";
        boolean matches10 = Pattern.matches(r10, input);
        System.out.println(input + "是有1~3位小数的正实数" + matches10);

        String r11 = "^[1-9]\\d*$";
        // "^([1-9][0-9]*){1,3}$"  或者 "^\+?[1-9][0-9]*$"
        boolean matches11 = Pattern.matches(r11, input);
        System.out.println(input + "是非零的正整数" + matches11);

        String r12 = "^-[1-9]\\d*$";
        boolean matches12 = Pattern.matches(r12, input);
        System.out.println(input + "是非零的负整数" + matches12);

        String r13 = "^\\d+$"; // "^[1-9]\d*|0$"
        boolean matches13 = Pattern.matches(r13, input);
        System.out.println(input + "是非负整数" + matches13);

        // "^((-\\d+)|(0+))$"
        String r14 = "^-[1-9]\\d*|0$";
        boolean matches14 = Pattern.matches(r14, input);
        System.out.println(input + "是非正整数" + matches14);

        // "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$"
        String r15 = "^\\d+(\\.\\d+)?$";
        boolean matches15 = Pattern.matches(r15, input);
        System.out.println(input + "是非负浮点数" + matches15);

        // "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$"
        String r16 = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
        boolean matches16 = Pattern.matches(r16, input);
        System.out.println(input + "是非正浮点数" + matches16);

        // "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"
        String r17 = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
        boolean matches17 = Pattern.matches(r17, input);
        System.out.println(input + "是正浮点数" + matches17);

        // "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"
        String r18 = "^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$";
        boolean matches18 = Pattern.matches(r18, input);
        System.out.println(input + "是负浮点数" + matches18);

        // "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$"
        String r19 = "^(-?\\d+)(\\.\\d+)?$";
        boolean matches19 = Pattern.matches(r19, input);
        System.out.println(input + "是浮点数" + matches19);
    }

    private static void test4() {
        // 校验字符的表达式
        String input = "eess3123中s";

        String r1 = "^[\\u4e00-\\u9fa5]{0,}$";
        boolean matches1 = Pattern.matches(r1, input);
        System.out.println(input + "是汉字" + matches1);

        // "^[A-Za-z0-9]{4,40}$"
        String r2 = "^[A-Za-z0-9]+$";
        boolean matches2 = Pattern.matches(r2, input);
        System.out.println(input + "是英文和数字" + matches2);

        String r3 = "^.{3,20}$";
        boolean matches3 = Pattern.matches(r3, input);
        System.out.println(input + "是长度为3-20的所有字符" + matches3);

        String r4 = "^[A-Za-z]+$";
        boolean matches4 = Pattern.matches(r4, input);
        System.out.println(input + "是由26个英文字母组成的字符串" + matches4);

        String r5 = "^[A-Z]+$";
        boolean matches5 = Pattern.matches(r5, input);
        System.out.println(input + "是由26个大写英文字母组成的字符串" + matches5);

        String r6 = "^[a-z]+$";
        boolean matches6 = Pattern.matches(r6, input);
        System.out.println(input + "是由26个小写英文字母组成的字符串" + matches6);

        String r7 = "^[A-Za-z0-9]+$";
        boolean matches7 = Pattern.matches(r7, input);
        System.out.println(input + "是由数字和26个英文字母组成的字符串" + matches7);

        // "^\\w{3,20}$"
        String r8 = "^\\w+$";
        boolean matches8 = Pattern.matches(r8, input);
        System.out.println(input + "是由数字、26个英文字母或者下划线组成的字符串" + matches8);

        String r9 = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$";
        boolean matches9 = Pattern.matches(r9, input);
        System.out.println(input + "是中文、英文、数字包括下划线" + matches9);

        String r10 = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
        boolean matches10 = Pattern.matches(r10, input);
        System.out.println(input + "是中文、英文、数字但不包括下划线等符号" + matches10);

        String r11 = "[^%&',;=?$\\x22]+";
        boolean matches11 = Pattern.matches(r11, input);
        System.out.println(input + "是可以输入含有^%&',;=?$\\\"等字符" + matches11);

        String r12 = "[^~\\x22]+";
        boolean matches12 = Pattern.matches(r12, input);
        System.out.println(input + "是禁止输入含有~的字符" + matches12);
    }

    private static void test5() {
        // 汉字
        String r1 = "/[\\u4E00-\\u9FA5]/";
        // 全角符号
        String r2 = "/[\\uFF00-\\uFFFF]/";
        // 半角符号
        String r3 = "/[\\u0000-\\u00FF]/";
    }

    private static void test6() {
        String input = "333www.haha.com";

        String r1 = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w)*\\.\\w+([-.]\\w)*$";
        boolean matches1 = Pattern.matches(r1, input);
        System.out.println(input + "是Email地址：" + matches1);

        String r2 ="[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
        boolean matches2 = Pattern.matches(r2, input);
        System.out.println(input + "是域名" + matches2);

    }


}
