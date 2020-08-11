package com.demo.designpatterns.strategymode.examp03;

/**
 * @ClassName MiddleStrategy
 * @Description 攻取西川的中计策
 * @Author hup
 * @Date 2020/8/10 13:57
 * @Version 1.0
 */
public class MiddleStrategy implements IOccupationStrategyWestOfSiChuan {
    @Override
    public void occupationWestOfSiChuan(String msg) {
        System.out.println("杨怀、高沛是蜀中名将，手下有精锐部队，而且据守关头，我们可以装作要回荆州，" +
                "引他们轻骑来见，可就此将其擒杀，而后进兵成都，此为中计");
    }
}
