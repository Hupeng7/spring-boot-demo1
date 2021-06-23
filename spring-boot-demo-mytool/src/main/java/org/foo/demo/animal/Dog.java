package org.foo.demo.animal;

/**
 * @ClassName Dog
 * @Description
 * @Author H
 * @Date 2021/6/23 16:51
 * @Version 1.0
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("dog wang wang wang");
    }
}
