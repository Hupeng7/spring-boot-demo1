package com.example.springbootdemomytool.utils.threadpooldemo.producerandconsumer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName ClientTest
 * @Description from https://mp.weixin.qq.com/s/vWUcPjpqqamU5D-AAaNVSQ
 * 生产者 - 消费者模型 Producer-consumer problem
 * 简单来说，这个模型是由两类线程构成：
 * 生产者线程：“生产”产品，并把产品放到一个队列里；
 * 消费者线程：“消费”产品。
 * 队列：数据缓存区。
 * <p>
 * 生产者和消费者之间的解藕和异步。
 * 但是呢，生产者和消费者之间也不能完全没有联系的。
 * 如果队列里的产品已经满了，生产者就不能继续生产；
 * 如果队列里的产品从无到有，生产者就得通知一下消费者，告诉它可以来消费了；
 * 如果队列里已经没有产品了，消费者也无法继续消费；
 * 如果队列里的产品从满到不满，消费者也得去通知下生产者，说你可以来生产了。
 * 所以它们之间还需要有协作，最经典的就是使用 Object 类里自带的 wait() 和 notify() 或者 notifyAll() 的消息通知机制。
 * 上述描述中的等着，其实就是用 wait() 来实现的；
 * 而通知，就是 notify() 或者 notifyAll() 。
 * 那么基于这种消息通知机制，我们还能够平衡生产者和消费者之间的速度差异。
 * 如果生产者的生产速度很慢，但是消费者消费的很快，就像是我们每月工资就发两次，但是每天都要花钱，也就是 1:15.
 * 那么我们就需要调整生产者（发工资）为 15 个线程，消费者保持 1 个线程，这样是不是很爽～
 * 总结下该模型的三大优点：
 * 解藕，异步，平衡速度差异。
 * @Author hup
 * @Date 2020/9/15 14:41
 * @Version 1.0
 */
public class ClientTest {
    public static void main(String[] args) {
        Queue<Product> queue = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            new Thread(new Producer(queue, 100)).start();
            new Thread(new Consumer(queue, 100)).start();
        }

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final queue size: " + queue.size());

    }

}
