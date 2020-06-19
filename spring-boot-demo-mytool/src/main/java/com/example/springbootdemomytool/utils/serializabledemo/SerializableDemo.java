package com.example.springbootdemomytool.utils.serializabledemo;

import com.example.springbootdemomytool.beans.User1;
import com.example.springbootdemomytool.beans.User2;
import com.example.springbootdemomytool.beans.User3;

import java.io.*;

/**
 * @ClassName SerializableDemo
 * @Description
 * @Author Leo
 * @Date 2020/6/19 14:39
 * @Version 1.0
 */
public class SerializableDemo {

    public static void main(String[] args) throws Exception, IOException {
        System.out.println("Serializable==============");
        // 初始化对象
        User1 user = new User1();
        user.setName("tom");
        user.setAge(18);
        System.out.println(user);
        // 序列化对象到文件中
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("template"));
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        // 反序列化
        File file = new File("template");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        User1 newUser = (User1) objectInputStream.readObject();
        System.out.println(newUser.toString());
        objectInputStream.close();
        System.out.println("Externalizable====================");
        // 初始化对象
        User2 user2 = new User2();
        user2.setName("jerry");
        user2.setAge(17);
        System.out.println(user2);
        // 序列化对象到文件中
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("template1"));
        objectOutputStream1.writeObject(user2);
        objectOutputStream1.close();
        // 反序列化
        File file1 = new File("template1");
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream(file1));
        User2 user21 = (User2) objectInputStream1.readObject();
        System.out.println(user21.toString());
        objectInputStream1.close();
        System.out.println("Externalizable22222====================");
        /**
         * 报错 详见User3注释内容
         * Exception in thread "main" java.io.EOFException
         */
        // 初始化对象
        try {
            User3 user3 = new User3();
            user3.setName("jack");
            user3.setAge(17);
            System.out.println(user3);
            // 序列化对象到文件中
            ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(new FileOutputStream("template3"));
            objectOutputStream3.writeObject(user3);
            objectOutputStream3.close();
            // 反序列化
            File file3 = new File("template3");
            ObjectInputStream objectInputStream3 = new ObjectInputStream(new FileInputStream(file3));
            User3 user31 = (User3) objectInputStream3.readObject();
            System.out.println(user31.toString());
            objectInputStream3.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
