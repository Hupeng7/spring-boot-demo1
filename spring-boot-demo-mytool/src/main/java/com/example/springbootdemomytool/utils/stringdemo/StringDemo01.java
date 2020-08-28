package com.example.springbootdemomytool.utils.stringdemo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * @ClassName StringDemo01
 * @Description
 * @Author hup
 * @Date 2020/8/25 11:36
 * @Version 1.0
 */
public class StringDemo01 {
    public static void main(String[] args) {
        String str = "asdabdfaefwef";
        // 判断一个字符串是否为空，null或"" 都返回true
        StringUtils.isEmpty(str);
        // 是否是数字
        StringUtils.isNumeric(str);
        // 最左边两个字符
        StringUtils.left(str, 2);
        // 统计字符串出现的次数
        String subString = "d";
        StringUtils.countMatches(str, subString);
        // 转义XMl标识
        StringEscapeUtils.escapeXml(str);
        // 随机生成，长度为10的仅字母的字符串
        RandomStringUtils.randomAlphabetic(10);
        // 随机生成，长度为10的ASCII字符串
        RandomStringUtils.randomAscii(10);
        // 以一个单词为操作对象，首字母大写，输出结果为：Abc Bcd
        WordUtils.capitalize("abc bcd");



    }


}
