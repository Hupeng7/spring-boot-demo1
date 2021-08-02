package com.example.springbootdemomytool.utils.numberdemo;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @ClassName NumberDemo01
 * @Description
 * @Author H
 * @Date 2021/8/2 9:58
 * @Version 1.0
 */
public class NumberDemo01 {
    public static void main(String[] args) {
        numTest1();
        numTest2();
    }

    public static void numTest1() {
        // 不推荐
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("a values is: " + a);
        System.out.println("==============================");
        // 推荐 使用参数类型为String的构造函数
        BigDecimal b = new BigDecimal("0.1");
        System.out.println("b values is: " + b);
    }

    public static void numTest2() {
        // 简历货币格式化引用
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        // 建立百分比格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(3);

        BigDecimal loanAmount = new BigDecimal("15000.48");
        BigDecimal interestRate = new BigDecimal("0.008");
        BigDecimal interest = loanAmount.multiply(interestRate);
        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
    }


}
