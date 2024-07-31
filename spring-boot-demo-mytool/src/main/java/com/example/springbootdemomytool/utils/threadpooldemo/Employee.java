package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Employee
 * @Description
 * @Author H
 * @Date 2024/7/30 18:56
 * @Version 1.0
 */
public class Employee implements Callable<String> {
    private CountDownLatch countDownLatch;
    private int employeeIndex;

    public Employee(CountDownLatch countDownLatch, int employeeIndex) {
        this.countDownLatch = countDownLatch;
        this.employeeIndex = employeeIndex;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println("员工: " + employeeIndex + ",go together");
            Thread.sleep(10);
            System.out.println("" + employeeIndex + ",already arrived");
            return "e-"+employeeIndex;
        } catch (Exception e) {
            System.out.println("Employee call error");
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("员工：" + employeeIndex + ",do other thing,1,2,3...");
        }
        return null;
    }

}
