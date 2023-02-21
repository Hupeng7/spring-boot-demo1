package com.example.springbootdemomytool.utils.threaddemo.threaddemo02;

/**
 * @ClassName SellTicket
 * @Description
 * @Author H
 * @Date 2023/1/11 18:30
 * @Version 1.0
 */
public class SellTicket implements Runnable {
    // 为了让多个线程对象共享这100张票和锁对象（同一把锁），我们应该用静态修饰
    private int tickets = 100;
    // 创建锁对象
    private Object object = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (tickets--) + "张票");
                }
            }
        }
    }
}
