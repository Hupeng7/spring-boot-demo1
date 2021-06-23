package org.foo.demo.animal;

/**
 * @ClassName Cat
 * @Description
 * @Author H
 * @Date 2021/6/23 16:50
 * @Version 1.0
 */
public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("cat miao miao miao");
    }
}
