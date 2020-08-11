package com.demo.springbootdemoguava.demo;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName IOTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 18:15
 * @Version 1.0
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        String a = "hello world!";

        // 从input stream读取byte数组
        byte[] bytes = ByteStreams.toByteArray(new ByteArrayInputStream(a.getBytes(StandardCharsets.UTF_8)));
        System.out.println("ByteStream.toByteArray : " + new String(bytes, StandardCharsets.UTF_8));

        // 从input stream 读取到output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteStreams.copy(new ByteArrayInputStream(a.getBytes()), out);
        System.out.println("ByteStreams.copy : " + out.toString());

        // 从reader中读取字符串信息
        String result = CharStreams.toString(new InputStreamReader(new ByteArrayInputStream(a.getBytes()), StandardCharsets.UTF_8));
        System.out.println("CharStreams.toString : " + result);

        // Files的一些工具方法
        System.out.println("Files.getFileExtension : " + Files.getFileExtension("aaa.jpg"));
        System.out.println("Files.getNameWithoutExtension : " + Files.getNameWithoutExtension("aaa.jpg"));

    }
}
