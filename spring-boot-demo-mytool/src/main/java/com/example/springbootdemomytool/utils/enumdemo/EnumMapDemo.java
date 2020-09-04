package com.example.springbootdemomytool.utils.enumdemo;

import java.util.*;

/**
 * @ClassName EnumMapDemo
 * @Description EnumMap
 * @Author hup
 * @Date 2020/9/4 15:25
 * @Version 1.0
 */
public class EnumMapDemo {
    public static void main(String[] args) {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes("C001", ColorC.BLUE));
        list.add(new Clothes("C002", ColorC.YELLOW));
        list.add(new Clothes("C003", ColorC.RED));
        list.add(new Clothes("C004", ColorC.GREEN));
        list.add(new Clothes("C005", ColorC.BLUE));
        list.add(new Clothes("C006", ColorC.BLUE));
        list.add(new Clothes("C007", ColorC.RED));
        list.add(new Clothes("C008", ColorC.YELLOW));
        list.add(new Clothes("C009", ColorC.YELLOW));
        list.add(new Clothes("C010", ColorC.GREEN));

        // 方案1：使用HashMap
        Map<String, Integer> map = new HashMap<>();
        for (Clothes clothes : list) {
            String colorName = clothes.getColor().name();
            Integer count = map.get(colorName);
            if (count != null) {
                map.put(colorName, count + 1);
            } else {
                map.put(colorName, 1);
            }
        }
        System.out.println("方案1的结果：" + map.toString());

        System.out.println("---------------------------------------");
        // 方案2：使用EnumMap
        Map<ColorC, Integer> enumMap = new EnumMap<ColorC, Integer>(ColorC.class);
        for (Clothes clothes : list) {
            ColorC colorC = clothes.getColor();
            Integer count = enumMap.get(colorC);
            if (count != null) {
                enumMap.put(colorC, count + 1);
            } else {
                enumMap.put(colorC, 1);
            }
        }
        System.out.println("方案2的结果：" + enumMap.toString());
        System.out.println("---------------------------------------");


    }

}
