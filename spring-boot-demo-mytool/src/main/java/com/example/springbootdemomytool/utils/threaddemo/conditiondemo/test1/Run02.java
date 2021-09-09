package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName Run02
 * @Description
 * @Author H
 * @Date 2021/8/6 14:03
 * @Version 1.0
 */
public class Run02 {
    public static void main(String[] args) throws InterruptedException {
        MyService02 myService02 = new MyService02();
        ThreadA02 threadA02 = new ThreadA02(myService02);
        threadA02.setName("A02");
        threadA02.start();
        ThreadB02 threadB02 = new ThreadB02(myService02);
        threadB02.setName("B02");
        threadB02.start();
        Thread.sleep(3000);
        myService02.signalAll();
    }
}
