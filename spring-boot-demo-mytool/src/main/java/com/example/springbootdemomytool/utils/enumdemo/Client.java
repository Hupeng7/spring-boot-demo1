package com.example.springbootdemomytool.utils.enumdemo;

import java.util.Arrays;

/**
 * @ClassName Client
 * @Description
 * @Author hup
 * @Date 2020/9/4 11:05
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        DayEnum dayEnum = DayEnum.FRIDAY;

        // 创建枚举数组
        DayEnum[] days = new DayEnum[]{
                DayEnum.MONDAY, DayEnum.TUESDAY, DayEnum.WEDNESDAY,
                DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY, DayEnum.SUNDAY
        };

        for (int i = 0; i < days.length; i++) {
            System.out.println("day[" + i + "].ordinal():" + days[i].ordinal());
        }

        System.out.println("--------------------------------------------");
        // 通过compareTo方法比较，实际上其内部是通过ordinal()值比较的
        System.out.println("days[0].compareTo(days[1]):" + days[0].compareTo(days[1]));
        System.out.println("days[0].compareTo(days[2]):" + days[0].compareTo(days[2]));

        System.out.println("--------------------------------------------");
        // 获取该枚举对象的Class对象引用，当然也可以通过getClass方法
        Class<?> clazz = days[0].getDeclaringClass();
        System.out.println("clazz:" + clazz);

        System.out.println("--------------------------------------------");
        // name()
        System.out.println("days[0].name()" + days[0].name());
        System.out.println("days[4].name()" + days[4].name());

        System.out.println("--------------------------------------------");
        // toString()
        System.out.println("days[0].toString():" + days[0].toString());
        System.out.println("days[2].toString():" + days[2].toString());

        System.out.println("--------------------------------------------");
        // valueOf()
        DayEnum dayEnum1 = Enum.valueOf(DayEnum.class, days[0].name());
        DayEnum dayEnum2 = Enum.valueOf(DayEnum.class, days[1].name());
        System.out.println("dayEnum1:" + dayEnum1);
        System.out.println("dayEnum2:" + dayEnum2);

        System.out.println("--------------------------------------------");
        // values()
        DayEnum[] days2 = DayEnum.values();
        System.out.println("days2:" + Arrays.toString(days2));
        DayEnum dayEnum3 = DayEnum.valueOf("WEDNESDAY");
        System.out.println("dayEnum3:" + dayEnum3);

        System.out.println("--------------------------------------------");
        Class<?> clazz1 = dayEnum3.getDeclaringClass();
        if (clazz1.isEnum()) {
            DayEnum[] dayEnums = (DayEnum[]) clazz1.getEnumConstants();
            System.out.println("dayEnums:" + Arrays.toString(dayEnums));
        }


        System.out.println("--------------------------------------------");
        // test Food
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;

    }
}
