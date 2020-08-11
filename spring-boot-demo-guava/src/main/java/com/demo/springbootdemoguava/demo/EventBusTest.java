package com.demo.springbootdemoguava.demo;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName EventBus
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 18:53
 * @Version 1.0
 */
public class EventBusTest {
    public static void main(String[] args) throws Exception {
        // 1拥有EventBus实例
        EventBus eventBus = new EventBus();

        // 2注册监听器
        eventBus.register(new EventBusTest());

        // 3发送消息。一般1和2在一起配置，3分布在代码各处
        eventBus.post(new CustomEvent("zhang san"));

        // 异步的EventBus
        EventBus asyncEventBus = new AsyncEventBus("AsyncEventBus",
                new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(80), new ThreadFactory() {
                    private AtomicInteger atomicInteger = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        System.out.println("do something......");
                        t.setName("AsyncEventBusExecutorService.Pool-" + atomicInteger.getAndIncrement());
                        return t;
                    }
                }));
        asyncEventBus.register(new EventBusTest());
        for (int i = 0; i < 100; i++) {
            asyncEventBus.post(new CustomEvent("zhang san" + i + "号"));
        }
        asyncEventBus.post(new CustomEvent("li si"));
        asyncEventBus.post(new CustomEvent2());

        // 正常退出
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }


    @Subscribe
    public void eventReceiver(CustomEvent changeEvent) {
        System.out.println("Thread name : " + Thread.currentThread().getName() + " ====> receive event: " + changeEvent);

    }

    private static class CustomEvent {
        private String name;

        public CustomEvent(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "CustomEvent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static class CustomEvent2 {
        @Override
        public String toString() {
            return "CustomEvent2{}";
        }
    }

}
