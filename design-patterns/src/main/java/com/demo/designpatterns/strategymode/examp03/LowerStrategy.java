package com.demo.designpatterns.strategymode.examp03;

/**
 * @ClassName LowerStrategy
 * @Description 攻取西川的下策
 * @Author hup
 * @Date 2020/8/10 14:01
 * @Version 1.0
 */
public class LowerStrategy implements IOccupationStrategyWestOfSiChuan {
    @Override
    public void occupationWestOfSiChuan(String msg) {
        System.out.println("退还白帝，连引荆州，慢慢进图益州，此为下计");
    }
}
