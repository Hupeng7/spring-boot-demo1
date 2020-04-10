package com.example.springbootdemomytool;

import com.example.springbootdemomytool.utils.reflectDemo.ReflectClass;
import org.junit.Test;

/**
 * @ClassName ReflectClassTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/9 10:31
 * @Version 1.0
 */
public class ReflectClassTest extends SpringBootDemoMytoolApplicationTests {

    @Test
    public void testReflect() {
        // 创建对象
        ReflectClass.reflectNewInstance();
        // 反射私有的构造方法
        ReflectClass.reflectPrivateConstructor();
        // 反射私有属性
        ReflectClass.reflectPrivateField();
        // 反射私有方法
        ReflectClass.reflectPrivateMethod();
    }

    @Test
    public void testReflect2() {
        ReflectClass.reflectNewInstance();
        System.out.println("testReflect2 ====");
    }
}
