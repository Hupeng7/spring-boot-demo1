package com.demo.designpatterns.proxypattern.examp01;

/**
 * @ClassName RealSubject
 * @Description
 * @Author hup
 * @Date 2020/8/19 18:21
 * @Version 1.0
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real subject do something...");
    }
}
