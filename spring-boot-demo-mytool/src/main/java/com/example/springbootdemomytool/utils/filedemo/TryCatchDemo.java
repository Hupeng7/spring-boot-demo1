package com.example.springbootdemomytool.utils.filedemo;

import java.io.*;

/**
 * @ClassName TryCatchDemo
 * @Description try-with-resources
 * try后面括号里的流必须要继承 java.lang.AutoCloseable
 * @Author Leo
 * @Date 2020/6/24 17:11
 * @Version 1.0
 */
public class TryCatchDemo {

    public static void fileCopy(File one, File two) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(one);
            fileOutputStream = new FileOutputStream(two);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileCopy2(File one, File two) {
        try (FileInputStream fileInputStream = new FileInputStream(one);
             FileOutputStream fileOutputStream = new FileOutputStream(two)) {
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(b)) != 0) {
                fileOutputStream.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
