package com.example.springbootdemomytool.utils.codedemo;

/**
 * @ClassName InnerClassDemo1
 * @Description 内部类
 * 内部类可以拥有 private 访问权限、protected 访问权限、public 访问权限及包访问权限。比如上面的例子，
 * 如果成员内部类 Inner 用 private 修饰，则只能在外部类的内部访问，如果用 public 修饰，则任何地方都能访问；
 * 如果用 protected 修饰，则只能在同一个包下或者继承外部类的情况下访问；
 * 如果是默认访问权限，则只能在同一个包下访问。这一点和外部类有一点不一样，外部类只能被 public 和包访问两种权限修饰。
 * 我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰。
 * @Author hup
 * @Date 2020/7/31 10:34
 * @Version 1.0
 */
public class InnerClassDemo1 {
    private double radius = 0;
    public static int count = 1;

    public InnerClassDemo1(double radius) {
        this.radius = radius;
        getDrawInstance().drawShape(); // 必须先创建成员内部类的对象，才能进行访问
    }

    public InnerClassDemo1() {
    }

    // 成员内部类
    class Draw { // 内部类
        public void drawShape() {
            System.out.println("这是成员内部类方法");
            System.out.println("外部类的private 成员: " + radius);
            System.out.println("外部类的静态成员: " + count);
        }
    }

    private Draw getDrawInstance() {
        return new Draw();
    }

    public static void main(String[] args) {
        // 第一种方式
        InnerClassDemo1 innerClassDemo1 = new InnerClassDemo1(10);
        Draw draw = innerClassDemo1.new Draw();

        // 第二种方式
        InnerClassDemo1.Draw drawInstance = innerClassDemo1.getDrawInstance();
    }

}
