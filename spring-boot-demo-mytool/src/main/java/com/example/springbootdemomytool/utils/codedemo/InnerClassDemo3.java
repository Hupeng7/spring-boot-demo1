package com.example.springbootdemomytool.utils.codedemo;

/**
 * @ClassName InnerClassDemo3
 * @Description 静态内部类
 * 静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。
 * 静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法，这点很好理解，
 * 因为在没有外部类的对象的情况下，可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾，
 * 因为外部类的非static成员必须依附于具体的对象。
 * @Author hup
 * @Date 2020/7/31 11:45
 * @Version 1.0
 */
public class InnerClassDemo3 {
    Outter.Inner inner = new Outter.Inner();
}

class Outter {
    int a = 10;
    static int b = 5;

    public Outter() {
    }

    static class Inner {
        public Inner() {
        }
    }

}