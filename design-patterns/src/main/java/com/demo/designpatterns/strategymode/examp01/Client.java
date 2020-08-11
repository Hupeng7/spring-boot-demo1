package com.demo.designpatterns.strategymode.examp01;

/**
 * @ClassName Client
 * @Description 外部客户端
 * @Author hup
 * @Date 2020/8/10 11:39
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 1.创建具体策略实现
        IStrategy strategy = new ConcreteStrategy2();
        // 2.在创建策略上下文的同时，将具体的策略实现对象注入到策略上下文当中
        StrategyContext context = new StrategyContext(strategy);
        // 3.在调用上下文对象的方法来完成对具体策略实现的回调
        context.contextMethod();

        StrategyContext contextNew = new StrategyContext(new ConcreteStrategy());
        contextNew.contextMethod();
    }

}
