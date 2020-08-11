package com.demo.designpatterns.strategymode.examp01;

/**
 * @ClassName IStrategy
 * @Description 策略接口
 *  参考：https://mp.weixin.qq.com/s/j-CAOlYpVw0VRW3vLx2l_A
 * @Author hup
 * @Date 2020/8/10 11:29
 * @Version 1.0
 */
public interface IStrategy {

    // 定义的抽象算法方法 来约束具体的算法实现方法
    public void algorithmMethod();
}
