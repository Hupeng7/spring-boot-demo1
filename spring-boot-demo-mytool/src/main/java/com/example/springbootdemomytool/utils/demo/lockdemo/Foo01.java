package com.example.springbootdemomytool.utils.demo.lockdemo;

/**
 * @ClassName Foo01
 * @Description
 * @Author hup
 * @Date 2020/8/21 10:35
 * @Version 1.0
 */
public class Foo01 {
    public static void main(String[] args) throws Exception {
        final Foo02 foo02 = new Foo02();
        // 定义一个线程
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                foo02.m1();
            }
        });
        t.start();
        // 等待t1线程启动完毕
        Thread.sleep(10);
        foo02.m2();

        Foo foo = new Foo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                foo.run();
            }
        }).start();

    }


    static class Foo02 {
        public synchronized void m1() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1 done...");
        }

        public synchronized void m2() {
            System.out.println("m2 done...");
        }
    }
}
