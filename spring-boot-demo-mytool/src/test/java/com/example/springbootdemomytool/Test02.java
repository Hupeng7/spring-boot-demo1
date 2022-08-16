package com.example.springbootdemomytool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName Test02
 * @Description
 * @Author H
 * @Date 2021/11/5 18:56
 * @Version 1.0
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        //new Test02().test();
        int randomScore = getRandomScore(80, 45, 10);
        System.out.println("random score:" + randomScore);
        int floor = (int) Math.floor(randomScore);
        System.out.println("random score floor:" + floor);

        /**
         * https://mp.weixin.qq.com/s/vOCjqLPKMLHl26cjUeTjLg
         * 1900年 因为历史bug 导致时钟回拨353s
         */
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str3 = "1900-01-01 08:05:42";
        String str4 = "1900-01-01 08:05:43";
        Date sDt3 = sf.parse(str3);
        Date sDt4 = sf.parse(str4);
        long ld3 = sDt3.getTime() / 1000;
        long ld4 = sDt4.getTime() / 1000;
        System.out.println(ld4 - ld3);

        double pow = Math.pow(4, 2);
        System.out.println(pow);

    }

    public static int getRandomScore(Integer x, Integer y, Integer z) {
        Random random = new Random();
        int f = (int) (Math.sqrt(z) * random.nextGaussian() + y);
        return f;
    }

    public void test() {
        String str = "hello";
        int a = 1;
        change(str);
        change(a);
        System.out.println(str);
        System.out.println(a);
    }

    private void change(String str) {
        str = "world";
    }

    private void change(int a) {
        a = a + 1;
    }


}
