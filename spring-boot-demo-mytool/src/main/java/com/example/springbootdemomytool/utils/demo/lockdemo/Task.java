package com.example.springbootdemomytool.utils.demo.lockdemo;

import java.util.Calendar;

/**
 * @ClassName Task
 * @Description
 * @Author hup
 * @Date 2020/8/20 19:03
 * @Version 1.0
 */
public class Task {
    public void doSomething() {
        try {
            // 每个线程等待2秒钟，注意将此时的线程转为WAITING状态
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        // 线程名称
        sb.append(" 线程名称: " + Thread.currentThread().getName());
        // 运行时间戳
        sb.append(", 执行时间： " + Calendar.getInstance().get(13) + "s");
        System.out.println(sb);
    }

}
