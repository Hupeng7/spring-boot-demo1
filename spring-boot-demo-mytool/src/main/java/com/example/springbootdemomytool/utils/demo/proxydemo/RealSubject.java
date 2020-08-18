package com.example.springbootdemomytool.utils.demo.proxydemo;

/**
 * @ClassName RealSubject
 * @Description TODO
 * @Author hup
 * @Date 2020/8/18 17:45
 * @Version 1.0
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real Subject do something...");
    }
}
