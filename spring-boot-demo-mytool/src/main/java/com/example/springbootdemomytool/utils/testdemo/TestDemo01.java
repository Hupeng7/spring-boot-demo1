package com.example.springbootdemomytool.utils.testdemo;

/**
 * @ClassName TestDemo01
 * @Description
 * @since 1.7数值字面量，不管整数还是浮点数，都允许在数字间插入任意多个下划线。
 * @Author H
 * @Date 2020/12/2 11:49
 * @Version 1.0
 */
public class TestDemo01 {
    public static void main(String[] args) {
        int i = 10_0000;
        System.out.println(i);

        float f = 1.00__30_2F;
        System.out.println(f);
    }
}
