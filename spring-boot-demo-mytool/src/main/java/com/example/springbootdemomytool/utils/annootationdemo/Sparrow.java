package com.example.springbootdemomytool.utils.annootationdemo;

/**
 * @ClassName Sparrow
 * @Description
 * @Author hup
 * @Date 2020/8/18 10:39
 * @Version 1.0
 */
public class Sparrow extends Bird {
    private Desc.Color color;

    public Sparrow() {
        color = Desc.Color.Grayish;
    }

    public Sparrow(Desc.Color _color) {
        color = _color;
    }

    @Override
    public Desc.Color getColor() {
        return color;
    }
}
