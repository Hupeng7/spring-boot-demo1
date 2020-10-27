package com.example.springbootdemomytool.utils.threadpooldemo.producerandconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

/**
 * @ClassName Consumer
 * @Description
 * @Author hup
 * @Date 2020/9/15 14:40
 * @Version 1.0
 */
@Slf4j
public class Consumer implements Runnable {
    private Queue<Product> queue;
    private int maxCapacity;

    public Consumer(Queue queue, int maxCapacity) {
        this.queue = queue;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void run() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("消费者" + Thread.currentThread().getName() + "等待中... Queue 已缺货，无法消费");
                    // 对象queue；区别于wait()不指定作用对象
                    queue.wait();
                    System.out.println("消费者" + Thread.currentThread().getName() + "退出等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.size() == maxCapacity) {
                queue.notifyAll();
            }

            Product product = queue.poll();
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了：" + product.getName());
        }
    }
}
