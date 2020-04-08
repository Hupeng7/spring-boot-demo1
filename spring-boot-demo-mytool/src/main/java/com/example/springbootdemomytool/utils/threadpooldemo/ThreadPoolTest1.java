package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName ThreadPoolTest1
 * @Description TODO
 * 需要注意下面几点：
 * 尽量使用手动的方式创建线程池，避免使用Executors工厂类
 * 根据场景，合理设置线程池的各个参数，包括线程池数量、队列、线程工厂和拒绝策略
 * 在调线程池submit()方法的时候，一定要尽量避免任务执行异常被吞掉的问题
 * 作者：juconcurrent
 * 链接：https://www.jianshu.com/p/7ab4ae9443b9
 * @Author Leo
 * @Date 2020/4/3 14:52
 * @Version 1.0
 */
public class ThreadPoolTest1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            // ps1 这个写法结果与ps2一样，但是少了返回值future，和get()方法报错了则不能知道
            // executor.submit(new DivTask(100, i));

            // ps2
            Future future = executor.submit(new DivTask(100, i));
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double result = a / b;
            System.out.println("【" + Thread.currentThread().getName() + "】 " + a + "/" + b + " = " + result);
        }
    }


}
