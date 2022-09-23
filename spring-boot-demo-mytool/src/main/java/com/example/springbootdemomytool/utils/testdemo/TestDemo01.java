package com.example.springbootdemomytool.utils.testdemo;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Random;

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
        //System.out.println(i);

        float f = 1.00__30_2F;
        //System.out.println(f);
        System.out.println("random is : "+getRandom());

        System.out.println(StringUtils.isBlank(""));

        File file1 = new File("/20210102/20000521_ttttt.txt");
        System.out.println(file1.getName());
    }

    /**
     * 随机数
     *
     * @return
     */
    public static String getRandom() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            sb.append("1234567890qwertyuiopasdfghjklzxcvbnm"
                    .charAt(random.nextInt("1234567890qwertyuiopasdfghjklzxcvbnm".length())));
        }
        return sb.toString();
    }
}
