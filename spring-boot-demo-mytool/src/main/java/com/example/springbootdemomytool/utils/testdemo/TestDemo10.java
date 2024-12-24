package com.example.springbootdemomytool.utils.testdemo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName TestDemo10
 * @Description 一个角色有33%的暴击率，每两次连续的攻击都不暴击时，第3次攻击必定暴击，怎么计算她的平均暴击率呀？
 * <p>
 * 解法一：
 * 为了方便，将33%当做1/3算。
 * 每次暴击之后不暴击的次数有三种0 1 2，概率分别为1/3，2/9，4/9，所以平均每次暴击需要1/3+2＊2/9+3＊4/9=19/9次攻击
 * 所以平均暴击率为9/19
 * <p>
 * 解法二：穷举，代码如下
 * @Author H
 * @Date 2023/12/13 18:53
 * @Version 1.0
 */
public class TestDemo10 {

    public static void test1() {
        int times = 100;
        int i = 0;
        int total = 0;
        int onHandleTimes = 1000000;
//        while (i < times) {
//            total += oneTime(onHandleTimes, i);
//            i++;
//        }
        System.out.println("hendle_times:" + times + " one_handle_times:" + onHandleTimes + " total_hits:" + total
                + " avg_hits:" + BigDecimal.valueOf(total).divide(BigDecimal.valueOf(onHandleTimes), 4, BigDecimal.ROUND_HALF_UP));

        for (int j = 3; j <= 12; j++) {
            System.out.println("do............." + j);
        }

    }

    private static int oneTime(int times, int idx) {
        int i = 0;
        int unHits = 0, hitCount = 0;
        while (i < times) {
            if (unHits == 2 || hit(33)) {
                hitCount++;
                unHits = 0;
            } else {
                unHits++;
            }
            i++;
        }
        System.out.println("inx:" + idx + " | times:" + times + " | hits:" + hitCount);
        return hitCount;
    }

    private static boolean hit(int probability) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        return randomNumber < probability;
    }

    public static void main(String[] args) {
        // test1();
        StringBuilder sb = new StringBuilder("select ( ");
        int sizeA = 2;
        for (int i = 1; i < sizeA; i++) {
            System.out.println(i);
            sb.append(" tableName " + i);
            int lastIndex = sizeA - 1;
            if (i < lastIndex && i >= 1) {
                sb.append(" + ");
            }
        }
        sb.append(") as totalCount");
        System.out.println(sb);

        Map<String, Object> params = new HashMap<>(6);
        params.put("mobile", "11111");
        params.put("idNo", "2222");
        params.put("name", "3333");
        params.put("group", "4444");
        params.put("updateTime", "5555");
        params.put("updateTime2", 666);
        params.put("method", "v1");

        String idNo = (String)params.get("idNo");
        String beforeMonth = (String)params.get("beforeMonth");
        Integer is_mnp = (Integer) params.get("is_mnp");
        Integer updateTime2 = (Integer) params.get("updateTime2");
        System.out.println(idNo);
        System.out.println(beforeMonth);
        System.out.println(is_mnp);
        System.out.println(updateTime2);

        String a1 = params.get("updateTime").toString();
        System.out.println(a1);

        String a = params.get("a").toString();
//        System.out.println(a);


    }

}


