package com.example.springbootdemomytool.utils.annootationdemo;

/**
 * @ClassName Bird
 * @Description
 * @Author hup
 * @Date 2020/8/18 10:38
 * @Version 1.0
 */
@Desc(c = Desc.Color.Yellow)
public abstract class Bird {
    public abstract Desc.Color getColor();
}
