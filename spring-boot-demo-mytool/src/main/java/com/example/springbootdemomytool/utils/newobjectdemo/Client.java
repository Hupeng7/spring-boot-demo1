package com.example.springbootdemomytool.utils.newobjectdemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @ClassName Client
 * @Description 五种方法创建java对象
 * https://mp.weixin.qq.com/s/h7jHzCZfhQ35Sya6ImN-Ag
 * @Author hup
 * @Date 2020/10/27 16:41
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1. Using new keyword -- Constructor call involved
        Employee emp1 = new Employee();
        emp1.setName("emp1");

        // 2. Using Class class's newInstance() method -- Constructor call involved
        Employee emp2 = Employee.class.newInstance();
        emp2.setName("emp2");

        // 3. Using Constructor class's newInstance() method -- Constructor call involved
        Constructor<Employee> constructor = Employee.class.getConstructor();
        Employee emp3 = constructor.newInstance();
        emp3.setName("emp3");

        // 4. Using clone() method -- Constructor call not involved
        Employee emp4 = (Employee) emp3.clone();
        emp4.setName("emp4");

        // 5. Using Deserialization -- Constructor call not involved
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"))) {
            out.writeObject(emp4);
        }

        Employee emp5;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"))) {
            emp5 = (Employee) in.readObject();
            emp5.setName("emp5");
        }
        System.out.println(emp1 + ", hashcode: " + emp1.hashCode());
        System.out.println(emp2 + ", hashcode: " + emp2.hashCode());
        System.out.println(emp3 + ", hashcode: " + emp3.hashCode());
        System.out.println(emp4 + ", hashcode: " + emp4.hashCode());
        System.out.println(emp5 + ", hashcode: " + emp5.hashCode());


    }


}
