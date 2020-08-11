package com.example.springbootdemomytool.utils.hutool;

import cn.hutool.core.convert.Convert;

/**
 * @ClassName HuToolDemo
 * @Description TODO
 * @Author hup
 * @Date 2020/7/17 11:43
 * @Version 1.0
 */
public class HuToolDemo {

    public static void main(String[] args) {
        double a = 6781652312316.651;
        String digitUppercase = Convert.digitToChinese(a);
        System.out.println(digitUppercase);

    }
}
