package com.example.springbootdemomytool.utils.codedemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestDemo
 * @Description
 * @Author hup
 * @Date 2020/8/18 17:06
 * @Version 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        Object o = list.stream().filter(e -> e > 0).findFirst().orElse(null);
        /**
         * 循环标签
         */
        doWhile();
    }

    public static void doWhile() {
        int i = 0;
        outer:
        while (true) {
            prt("outer while loop");
            while(true){
                i++;
                prt("i = "+i);
                if (i == 1){
                    prt("continue");
                    continue;
                }
                if (i == 3){
                    prt("continue outer");
                    continue  outer;
                }
                if (i == 5){
                    prt("break");
                    break;
                }
                if (i == 7){
                    prt("break outer");
                    break outer;
                }
            }
        }
    }

    static void prt(String s) {
        System.out.println(s);
    }
}
