package com.example.springbootdemomytool.utils.demo.proxydemo02;

/**
 * @ClassName RealSubject
 * @Description
 * @Author hup
 * @Date 2020/8/18 17:55
 * @Version 1.0
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real subject do something...");
    }
}
