package com.example.springbootdemomytool.utils.resttemplate;

import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestDebug
 * @Description
 * @Author Leo
 * @Date 2020/6/22 16:14
 * @Version 1.0
 */
@Component
public class DebugTest {

    @Test
    public void testDebug() {
        for (int i = 0; i < 100; i++) {
            System.out.println("you got " + i);
        }
    }

    @Test
    public void testDebug2() {
        int i = 99;
        method1(i);
    }

    private void method1(int i) {
        System.out.println("i:" + i);
        method2(i);
    }

    private void method2(int j) {
        j++;
        System.out.println("j:" + j);
    }

    @Test
    public void multiThreadTest() {
        new Thread(() -> {
            System.out.println("1.白日依山尽");
        }, "ThreadOne").start();
        new Thread(() -> {
            System.out.println("2.黄河入海流");
        }, "ThreadTwo").start();
        System.out.println("3.欲穷千里目");
        System.out.println("4.更上一层楼");
    }



}
