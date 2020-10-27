package com.example.springbootdemomytool.utils.threadpooldemo.producerandconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;

/**
 * @ClassName Producer
 * @Description
 * @Author hup
 * @Date 2020/9/15 14:31
 * @Version 1.0
 */
@Slf4j
public class Producer implements Runnable {
    private Queue<Product> queue;

    private int maxCapacity;

    public Producer(Queue<Product> queue, int maxCapacity) {
        this.queue = queue;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void run() {
        synchronized (queue) {
            while (queue.size() == maxCapacity) {
                try {
                    System.out.println("生产者" + Thread.currentThread().getName() + "等待中。。。Queue已达到最大容量，无法生产");
                    // 生产队列满了则等待 对象是queue；区别于wait()不指定作用对象
                    queue.wait();
                    System.out.println("生产者" + Thread.currentThread().getName() + "退出等待");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    log.error("生产者 error:{}", e);
                }
            }
            // 队列里的产品从无到有，需要通知在等待的消费者
            if (queue.size() == 0) {
                queue.notifyAll();
            }
            Random random = new Random();
            Integer i = random.nextInt();
            queue.offer(new Product("产品" + i.toString()));
            System.out.println("生产者1" + Thread.currentThread().getName() + "生产了产品: " + i.toString());
        }

    }
}
