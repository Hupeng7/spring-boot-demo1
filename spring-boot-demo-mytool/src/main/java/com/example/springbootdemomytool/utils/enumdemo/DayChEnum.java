package com.example.springbootdemomytool.utils.enumdemo;

/**
 * @ClassName DayChEnum
 * @Description
 * @Author hup
 * @Date 2020/9/4 11:46
 * @Version 1.0
 */
public enum DayChEnum {
    MONDAY("星期一"),

    TUESDAY("星期二"),

    WEDNESDAY("星期三"),

    THURSDAY("星期四"),

    FRIDAY("星期五"),

    SATURDAY("星期六"),

    SUNDAY("星期日");

    private DayChEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public String getDesc(DayChEnum dayChEnum) {
        for (DayChEnum day : DayChEnum.values()) {
            if (day.name().equals(dayChEnum.name())) {
                return day.getDesc();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        for (DayChEnum day : DayChEnum.values()) {
            System.out.println("name:" + day.name() + ",desc:" + day.getDesc());
        }
    }
}
