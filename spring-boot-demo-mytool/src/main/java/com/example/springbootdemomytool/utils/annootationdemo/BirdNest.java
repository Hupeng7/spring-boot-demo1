package com.example.springbootdemomytool.utils.annootationdemo;

/**
 * 鸟巢： 工厂方法模式
 * 1.枚举Sparrow1与 reproduce()方法中的Sparrow不同
 */
public enum BirdNest {
    Sparrow1;

    public Bird reproduce() {
        Desc bd = Sparrow.class.getAnnotation(Desc.class);
        // getClass().getAnnotation(Desc.class);
        System.out.println("bd" + bd);
        return bd == null ? new Sparrow() : new Sparrow(bd.c());
//        bd@com.example.springbootdemomytool.utils.annootationdemo.Desc(c=Yellow)
//        Bird's color is : Yellow
    }
}
