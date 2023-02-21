package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test10;

/**
 * @ClassName TestThread1
 * @Description 测试线程的可见性
 * @Author H
 * @Date 2022/11/15 17:28
 * @Version 1.0
 */
public class TestThread1 {
    private long count = 0;

    // 对count累加1000次
    private void addCount() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public long execute() throws InterruptedException {
        // 创建两个线程 执行count的累加操作
        Thread threadA = new Thread(() -> {
            addCount();
        });

        Thread threadB = new Thread(() -> {
            addCount();
        });
        // 启动线程
        threadA.start();
        threadB.start();

        // 等待线程执行结束
        threadA.join();
        threadB.join();

        // 返回结果
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread1 testThread1 = new TestThread1();
        long count = testThread1.execute();
        System.out.println(count);
    }


}
