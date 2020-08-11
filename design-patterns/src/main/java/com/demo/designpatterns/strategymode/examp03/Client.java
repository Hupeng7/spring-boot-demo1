package com.demo.designpatterns.strategymode.examp03;

/**
 * @ClassName Client
 * @Description 此时外部客户端相当于刘备，不管具体采用什么计策，只要结果（成功的攻下西川）
 * @Author hup
 * @Date 2020/8/10 14:06
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        OccupationContext context = new OccupationContext();
        // 激励不够
        context.occupationWestOfSiChuan("拿下西川");
        System.out.println("********************************");
        context.occupationWestOfSiChuan("拿下西川之后，人人有赏！");
    }


}
