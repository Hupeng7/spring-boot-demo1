package com.example.springbootdemomytool.utils.numberdemo;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @ClassName NumberDemo02
 * @Description BigDecimal
 * # 舍入模式  #加减乘除
 * http://t.zoukankan.com/zq1003-p-15184513.html
 * @Author H
 * @Date 2022/7/14 14:01
 * @Version 1.0
 */
public class NumberDemo02 {

    public static void main(String[] args) {
        /**
         * 1 创建
         */
        // new 对象
        BigDecimal a = new BigDecimal("3");
        // 内部静态
        BigDecimal b = BigDecimal.valueOf(23L);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal d = BigDecimal.TEN;
        BigDecimal e = BigDecimal.ZERO;

        /**
         * 2 加减乘除 精度（除不尽情况报异常） 一般配合 scale方法 + 舍入方法
         */
        // 加法 取2位小数
        BigDecimal add = b.add(a).setScale(2, BigDecimal.ROUND_HALF_UP);

        // 减法 取1位小数
        BigDecimal substract = b.subtract(a).setScale(1, BigDecimal.ROUND_HALF_DOWN);

        // 乘法 取3位小数
        BigDecimal multiplyA = b.multiply(a).setScale(3, BigDecimal.ROUND_FLOOR);

        // 除法 取2位小数 divideA写法是错误的 先除 除不尽就已经报异常了
        //BigDecimal divideA = b.divide(a).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal divideB = b.divide(a, 2, BigDecimal.ROUND_HALF_UP);

        /**
         * 3 舍入模式
         */
        // RoundingMode 舍入模式8种 小数取舍测试
        BigDecimal f = BigDecimal.valueOf(10);
        BigDecimal g = BigDecimal.valueOf(3);
        BigDecimal k = BigDecimal.valueOf(0.5);
        BigDecimal m = BigDecimal.valueOf(0.555);
        BigDecimal l = BigDecimal.valueOf(1);

        // 3.1 ROUND_UP 小数位向上取整 3.333 -> 3.34
        BigDecimal divide1 = f.divide(g, 2, BigDecimal.ROUND_UP);

        // 3.2 ROUND_DOWN 向下取整 3.333 -> 3.33
        BigDecimal divide2 = f.divide(g, 2, BigDecimal.ROUND_DOWN);

        // 3.3 ROUND_HALF_UP 四舍五入 3.333 -> 3.33
        BigDecimal divide3 = f.divide(g, 2, BigDecimal.ROUND_HALF_UP);

        /**
         * 3.4 ROUND_HALF_DOWN 五舍六入
         * 0.5 -> 0
         * 0.555 -> 0.5
         */
        BigDecimal divide4 = k.divide(l, 0, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal divide5 = m.divide(l, 2, BigDecimal.ROUND_HALF_DOWN);
        System.out.printf("divide4:%s ;divide5:%s ", divide4, divide5);

        /**
         * 3.5 ROUND_FLOOR 负无穷
         * 正数 数字部分-向下取整 round_up 0.555->0.55 divide6=0.55
         * 负数 数字部分-向上取整 round_down -0.555-> -0.56 divide7=-0.56
         * 不管怎么取 最后的数往小的取 负无穷模式
         */
        BigDecimal n = BigDecimal.valueOf(0.555);
        BigDecimal o = BigDecimal.valueOf(1);
        BigDecimal j = BigDecimal.valueOf(-0.555);
        BigDecimal divide6 = n.divide(o, 2, BigDecimal.ROUND_FLOOR);
        BigDecimal divide7 = j.divide(o, 2, BigDecimal.ROUND_FLOOR);

        /**
         * 3.6 ROUND_CEILING 正无穷
         * 正数 数字部分-向上取整 round_up 0.555-> 0.56
         * 负数 数字部分-向下取整 round_down -0.555 -> -0.55
         * 不管怎么取 最后的数往大的取 正无穷模式
         */
        BigDecimal divide8 = n.divide(o, 2, BigDecimal.ROUND_CEILING);
        BigDecimal divide9 = j.divide(o, 2, BigDecimal.ROUND_CEILING);

        /**
         * 3.7 ROUND_HALF_EVEN 银行家舍入法
         *  小数第二位是奇数 四舍五入 round_half_up （四舍） 0.554-> 0.55
         *  小数第二位是奇数 四舍五入 round_half_up （五入） 0.555-> 0.56
         *
         *  小数第二位是偶数 五舍六入 round_half_down (五舍) 0.565-> 0.56
         *  小数第二位是偶数 五舍六入 round_half_down (六入) 0.566-> 0.57
         */
        BigDecimal x = BigDecimal.valueOf(0.555);
        BigDecimal r = BigDecimal.valueOf(0.554);
        BigDecimal z = BigDecimal.valueOf(0.565);
        BigDecimal s = BigDecimal.valueOf(0.566);
        BigDecimal y = BigDecimal.valueOf(1);
        BigDecimal divide00 = x.divide(y, 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal divide01 = r.divide(y, 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal divide02 = z.divide(y, 2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal divide03 = s.divide(y, 2, BigDecimal.ROUND_HALF_EVEN);
        System.out.printf("\nROUND_HALF_EVEN\ndivide00:%s, \ndivide01:%s, \ndivide02:%s, \ndivide03:%s, \n", divide00, divide01, divide02, divide03);

        /**
         * 3.8 ROUND_UNNECESSARY 不需要舍入
         * 精确值 不需要舍入 有精度会报错 divide14
         */
        // BigDecimal divide14 = s.divide(y, 2, BigDecimal.ROUND_UNNECESSARY);
        BigDecimal divide15 = s.divide(y, BigDecimal.ROUND_UNNECESSARY);

        /**
         * 组合使用
         * (a*b)/(10-c) +d
         */
        BigDecimal add1 = a.multiply(b).divide(BigDecimal.TEN.subtract(c), 2, BigDecimal.ROUND_UP).add(d);
        System.out.println(add1);

        /**
         * 5.1 相等
         * 比较：
         *  equals: 比较精度
         *  compareTo: 不比较精度（一般用这个）
         *   至于问为什么两个equals 会有不同的值 已经提示了
         */
        BigDecimal bigC = new BigDecimal(1.2);
        BigDecimal bigE = new BigDecimal(1.2000000000);
        boolean equals = bigE.equals(bigC);
        // true
        System.out.println(bigE.equals(bigC));
        System.out.println(bigE.compareTo(bigC));

        BigDecimal z1 = new BigDecimal("0");
        BigDecimal z2 = new BigDecimal("0.0");
        // false
        System.out.println(z1.equals(z2));
        System.out.println(z1.compareTo(z2));

        /**
         * 5.2 max 最大值
         */
        BigDecimal bigD = new BigDecimal(6);
        BigDecimal max = bigD.max(bigC);

        /**
         * 5.3 min 最小值
         */
        BigDecimal min = bigD.min(bigC);

        /**
         * 5.4 pow 幂
         */
        BigDecimal pow = bigD.pow(2);


        /**
         * 5.5 round  四舍五入 2.345 -> 2.3 round=2.3
         */
        BigDecimal bigF = new BigDecimal(2.345);
        MathContext math = new MathContext(2);
        BigDecimal round = bigF.round(math);

        // 5.6 abs 绝对值 -23 ->23
        BigDecimal bigG = new BigDecimal("-23");
        BigDecimal abs = bigG.abs();
        System.out.println(abs);

        /**
         * 5.7 小数点移动
         * movePointLeft 小数点左移
         * movePointRight 小数点右移
         */
        BigDecimal bigH = new BigDecimal("23.4567");
        BigDecimal bigDecimal = bigH.movePointLeft(1);
        BigDecimal bigDecimal1 = bigH.movePointRight(1);

        /**
         * 5.8 negate 取反 23.4567 -> -23.4567
         */
        BigDecimal negate = bigH.negate();
        System.out.println(negate);

        /**
         * 5.9 plus 跟round 一样 底层都是plus
         */
        MathContext math1 = new MathContext(3);
        BigDecimal plus = bigH.plus(math1);
        System.out.println(plus);

        /**
         * ulp 返回最后一位的位置 都是1
         * 231212 ->1
         * 2.31212 -> 0.00001
         */
        BigDecimal bigK = new BigDecimal("231212");
        BigDecimal bigo = new BigDecimal("2.31212");
        BigDecimal ulp = bigo.ulp();

        /**
         * 6 转换
         */
        BigDecimal bigP = new BigDecimal("2312");
        bigP.doubleValue();
        bigP.longValue();
        bigP.intValue();
        bigP.byteValue();
        bigP.shortValue();
        bigP.byteValue();
        /**
         * toString 数字过长 会采用科学计数法
         * toPlainString 这个正常显示
         */
        bigP.toString();
        bigP.toPlainString();
        
    }
}
