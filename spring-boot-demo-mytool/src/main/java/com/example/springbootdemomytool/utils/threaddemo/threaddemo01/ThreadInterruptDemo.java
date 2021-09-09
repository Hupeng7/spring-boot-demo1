package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadInterruptDemo
 * @Description
 * @Author H
 * @Date 2021/8/11 16:46
 * @Version 1.0
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new InterruptThread("thread_1");
        thread.start();
        Thread.sleep(1);
        System.out.println(thread.getName() + "线程设置：interrupt");
        thread.interrupt();
    }
}

class InterruptThread extends Thread {
    public InterruptThread(String name) {
        super(name);
    }

//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName() + "线程开始！");
//        for (int i = 0; i < 1000; i++) {
//            try {
//                Thread.sleep(0);
//                System.out.println("" + (i + 1));
//            } catch (InterruptedException e) {
//                System.out.println(Thread.currentThread().getName() + "线程捕获异常，退出循环！");
//                break;
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + "线程结束！");
//    }

    // 异常法
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName() + "线程开始");
//        try {
//            for (int i = 0; i < 1000; i++) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println(Thread.currentThread().getName() +"线程停止状态！");
//                    throw new InterruptedException();
//                }
//                Thread.sleep(0);
//                System.out.println("" + (i + 1));
//            }
//        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + "线程捕获异常，退出循环！");
//            e.printStackTrace();
//        }
//    }

    // return法
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始！");
        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(0);
                System.out.println("" + (i + 1));
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "线程捕获异常，退出循环！isInterrupted():" + isInterrupted());
            e.printStackTrace();
            return;
        }
        System.out.println(Thread.currentThread().getName() + "线程结束！");
    }
}
