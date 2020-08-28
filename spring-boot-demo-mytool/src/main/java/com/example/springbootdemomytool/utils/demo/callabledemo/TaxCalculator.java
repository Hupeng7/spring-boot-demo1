package com.example.springbootdemomytool.utils.demo.callabledemo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TaxCalculator
 * @Description
 * @Author hup
 * @Date 2020/8/20 16:42
 * @Version 1.0
 */
public class TaxCalculator implements Callable<Integer> {
    private int seedMoney;

    public TaxCalculator(int _seedMoney) {
        seedMoney = _seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(10000L);
        return seedMoney / 10;
    }
}
