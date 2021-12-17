package com.example.springbootdemomytool.utils.threaddemo.threaddemo00;

/**
 * @ClassName Signal
 * @Description 线程A输出0，然后线程B输出1，再然后线程A输出2…以此类推。
 * 需要注意的是， volatile 变量需要进⾏原⼦操作。
 * signal++ 并不是⼀个原⼦操作，所以我们需要使⽤ synchronized 给它“上锁
 * @Author H
 * @Date 2021/12/17 15:35
 * @Version 1.0
 */
public class Signal {
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 10) {
                if (signal % 2 == 0) {
                    System.out.println("ThreadA :" + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 10) {
                if (signal % 2 == 1) {
                    System.out.println("ThreadB :" + signal);
                    synchronized (this) {
                        signal = signal + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }


}
