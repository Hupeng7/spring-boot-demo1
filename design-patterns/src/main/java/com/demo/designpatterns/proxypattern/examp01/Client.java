package com.demo.designpatterns.proxypattern.examp01;

/**
 * @ClassName Client
 * @Description 代理模式
 * 优点：1.职责清晰
 * 2.高扩展性
 * 3.智能化
 *
 * @Author hup
 * @Date 2020/8/19 18:28
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();

    }

}
