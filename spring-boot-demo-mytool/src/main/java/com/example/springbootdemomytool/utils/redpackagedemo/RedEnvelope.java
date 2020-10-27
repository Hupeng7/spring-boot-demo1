package com.example.springbootdemomytool.utils.redpackagedemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * @ClassName RedEnvelope
 * @Description 抢红包demo
 *  有限次的测试 二倍均值法  比 线段切割法 更均匀
 * @Author hup
 * @Date 2020/9/17 16:45
 * @Version 1.0
 */
public class RedEnvelope {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("this is red envelope demo...");

        int number;
        double money;
        System.out.println("please input red envelope total money...");
        money = scanner.nextDouble();
        System.out.println("please input red envelope number...");
        number = scanner.nextInt();

        // 二倍均值法
        doubleMeanMethod(money, number);
        System.out.println();

        // 线段切割法
        lineSegmentCutting(money, number);
    }

    /**
     * 线段切割法
     * 手速版
     *
     * @param money
     * @param number
     */
    private static void lineSegmentCutting(double money, int number) {
        if (money < 0 && number < 1) {
            System.out.println("input error!");
        }
        double begin = 0;
        double end = money;
        double y = 0;
        for (int i = 0; i < number - 1; i++) {
            double nn = 0;
            double amount = nextDouble(begin, end);

            nn = amount - begin;
            System.out.println("第" + (i + 1) + ": " + format(nn));
            y += nn;
            begin = amount;
        }
        System.out.println("第" + number + ": " + format(end - begin));
        y += (end - begin);
        System.out.println("check total money :" + format(y));
    }

    /**
     * 二倍均值法
     * 公平版
     *
     * @param money
     * @param number
     * @return
     */
    public static List<Double> doubleMeanMethod(double money, int number) {
        List<Double> result = new ArrayList<>();
        if (money < 0 && number < 1) {
            return null;
        }
        double amount = 0;
        double sum = 0;
        int remainingNumber = number;
        int i = 1;
        while (remainingNumber > 1) {
            amount = nextDouble(0.01, 2 * (money / remainingNumber));
            sum += amount;
            System.out.println("第" + i + ": " + format(amount));
            money -= amount;
            remainingNumber--;
            result.add(amount);
            i++;
        }
        result.add(money);
        System.out.println("第" + i + ": " + format(money));
        sum += money;
        System.out.println("" + format(sum));

        return result;
    }

    /**
     * 生成min到max范围的浮点数
     *
     * @param min
     * @param max
     * @return
     */
    private static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }

    /**
     * 保留两位小数
     *
     * @param value
     * @return
     */
    public static String format(double value) {
        return new java.text.DecimalFormat("0.00").format(value);

    }

}
