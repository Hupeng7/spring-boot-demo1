package com.example.springbootdemomytool.utils.codedemo;

/**
 * @ClassName InnerClassDemo2
 * @Description 局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
 * 注意: 局部内部类就像是方法里面的一个局部变量一样，是不能有 public、protected、private 以及 static 修饰符的。
 * @Author hup
 * @Date 2020/7/31 10:59
 * @Version 1.0
 */
public class InnerClassDemo2 {
    public InnerClassDemo2() {
    }

    public People getWoman() {
        class Woman extends People {  // 局部内部类
            int age = 18;

            @Override
            int getAge(int age) {
                //this.age = age;
                // return age;
                return this.age;
            }
        }
        return new Woman();
    }

    public People getMan() {
        class Man extends People {
            int age = 19;

            @Override
            int getAge(int age) {
                age = 10; // 重新赋值
                return age;
            }
        }

        return new Man();
    }

    public static void main(String[] args) {
        InnerClassDemo2 innerClassDemo2 = new InnerClassDemo2();
        People man = innerClassDemo2.getMan();

        System.out.println("man: " + man.getAge(6));
        People woman = innerClassDemo2.getWoman();
        System.out.println("woman: " + woman.getAge(7));
        System.out.println(innerClassDemo2);
    }
}

class People {
    public People() {
    }

    int getAge(int age) {
        return age;
    }
}