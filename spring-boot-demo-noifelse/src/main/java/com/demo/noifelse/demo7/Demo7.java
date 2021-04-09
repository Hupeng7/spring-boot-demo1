package com.demo.noifelse.demo7;

/**
 * @ClassName Demo7
 * @Description
 * @Author H
 * @Date 2021/4/8 17:33
 * @Version 1.0
 */
public class Demo7 {

    public static void main(String[] args) {
        String medalType = "guest";
        if ("guest".equals(medalType)) {
            System.out.println("嘉宾勋章");
        } else if ("vip".equals(medalType)) {
            System.out.println("会员勋章");
        } else if ("guard".equals(medalType)) {
            System.out.println("展示守护勋章");
        }
    }
}
