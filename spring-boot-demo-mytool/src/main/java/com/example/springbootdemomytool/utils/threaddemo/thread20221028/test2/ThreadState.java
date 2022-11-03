package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test2;

/**
 * @ClassName ThreadState
 * @Description 线程的生命周期
 * NEW：初始状态，线程被构建，但是还没有调用start()方法。
 * RUNNABLE：可运行状态，可运行状态可以包括：运行中状态和就绪状态。
 * BLOCKED：阻塞状态，处于这个状态的线程需要等待其他线程释放锁或者等待进入synchronized。
 * WAITING：表示等待状态，处于该状态的线程需要等待其他线程对其进行通知或中断等操作，进而进入下一个状态。
 * TIME_WAITING：超时等待状态。可以在一定的时间自行返回。
 * TERMINATED：终止状态，当前线程执行完毕。
 *
 * 由以上输出的信息可以看出：名称为WaitingTimeThread的线程处于TIMED_WAITING状态；名称为WaitingStateThread的线程处于
 * WAITING状态；名称为BlockedThread-01的线程处于TIMED_WAITING状态；名称为BlockedThread-02的线程处于BLOCKED状
 * 态。
 * 注意：使用jps结合jstack命令可以分析线上生产环境的Java进程的异常信息。
 * @Author H
 * @Date 2022/10/28 17:26
 * @Version 1.0
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new WaitingTime(), "WaitingTimeThread").start();
        new Thread(new WaitingState(), "WaitingStateThread").start();

        // BlockedThread-01线程会抢到锁 BlockedThread-02线程会阻塞
        new Thread(new BlockedThread(), "BlockedThread-01").start();
        new Thread(new BlockedThread(), "BlockedThread-02").start();
    }
}
