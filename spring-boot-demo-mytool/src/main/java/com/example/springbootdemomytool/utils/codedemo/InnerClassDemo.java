package com.example.springbootdemomytool.utils.codedemo;

/**
 * @ClassName InnerClassDemo
 * @Description 内部类
 * @Author hup
 * @Date 2020/7/30 14:57
 * @Version 1.0
 */
public class InnerClassDemo {
    public static void main(String[] args) {
        System.out.println("下面是内部类的程序展示");
        // 创建外部类和内部类的方法有点不同
        A a = new A();
        A a1 = null;
        // Objects.requireNonNull(a1);
        A.B b = new A().new B();
        a.say2();
        b.sayit();

        System.out.println("现在开始匿名内部类程序的编写\n");
        Chouxiang c = new Chouxiang() {
            String name = "匿名内部类的姓名 too";

            @Override
            public void say3() {
                System.out.println("这是匿名内部类当中的方法，重写了抽象类的方法");
                System.out.println(name);
            }
        }; // 在使用匿名内部类的时候，当这个类在陈述完之后，是需要打分号的
        c.say3();
        System.out.println("我们来看看name到底是抽象类中的name还是匿名内部类当中的name :" + c.name);
        // 这就是所为的向上转型 现在我们再来试试接口的匿名内部类实现
        Jiekou j = new Jiekou() {
            @Override
            public void say5() {
                System.out.println("这是继承的接口当中的方法");
            }
        };
        j.say5();
    }
}

class A {
    int waibu = 12;

    public void say2() {
        System.out.println("这是外部类当中的方法");
    }

    class B {
        int neibu = 13;

        public void sayit() {
            System.out.println("这是内部类里面的方法");
            System.out.println("使用内部类和外部类当中的数值相加的结果是 : " + (neibu + waibu));
            //之所以内部类可以使用外部类的属性是因为在创建对象的时候，已经给内部类的对象附加了一个外部类的对象，内部类的对象是建立在外部类对象的基础上的。
        }

    }

}

//虽然内部类的程序已经成功了，但是匿名内部类的程序还没有成功，现在我们来创建一个匿名内部类(在主方法当中,首先应该创建一个抽象类或者接口)
abstract class Chouxiang {
    String name = "匿名内部类的姓名"; // 抽象类的属性是不会被调用的，除了方法

    public void say3() {
        System.out.println("这是抽象类当中的方法，抽象类当中是允许有具体方法来进行实现的，接口不行");
    }

}

interface Jiekou {
    public void say5();
}

