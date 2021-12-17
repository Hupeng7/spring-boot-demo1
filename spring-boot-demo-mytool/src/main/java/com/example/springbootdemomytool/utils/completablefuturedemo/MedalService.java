package com.example.springbootdemomytool.utils.completablefuturedemo;

/**
 * @ClassName MedalService
 * @Description
 * @Author H
 * @Date 2021/12/15 11:57
 * @Version 1.0
 */
public class MedalService {
    public MedalInfo getMedalInfo(Long userId) throws InterruptedException {
        Thread.sleep(500);
        return new MedalInfo("1", "守护勋章");
    }

}
