package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName Run03
 * @Description
 * @Author H
 * @Date 2021/8/6 14:27
 * @Version 1.0
 */
public class Run03 {
    public static void main(String[] args) throws InterruptedException {
        MyService03 myService03 = new MyService03();
        ThreadA03 threadA03 = new ThreadA03(myService03);
        threadA03.setName("A03");
        threadA03.start();
        ThreadB03 threadB03 = new ThreadB03(myService03);
        threadB03.setName("B03");
        threadB03.start();
        Thread.sleep(3000);
        myService03.signalAll_B();
    }

}
