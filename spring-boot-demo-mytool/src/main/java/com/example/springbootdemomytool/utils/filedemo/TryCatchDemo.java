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
            while ((len = fileInputStream.read(b)) != -1) {                fileOutputStream.write(b, 0, len);
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

    // Java byte[]方式读写文件 JDK1.6之前

    /**
     * 以byte[] 方式读取文件
     *
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static byte[] readFileByBytes(String fileName) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
            out = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[in.available()];
            for (int i = 0; (i = in.read(tempBytes)) != -1; ) {
                out.write(tempBytes, 0, i);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return out.toByteArray();
    }

    /**
     * 向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节
     * @param append   是否追加
     * @throws IOException
     */
    public static void writeFileByBytes(String fileName, byte[] bytes, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(fileName, append));
            out.write(bytes);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 从文件开头向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节
     * @throws IOException
     */
    public static void writeFileByBytes(String fileName, byte[] bytes) throws IOException {
        writeFileByBytes(fileName, bytes, false);
    }

    // JDK1.7以后实现

    /**
     * 以byte[] 方式读取文件
     *
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static byte[] readFileByBytes7(String fileName) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(fileName));
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            byte[] tempBytes = new byte[in.available()];
            for (int i = 0; (i = in.read(tempBytes)) != -1; ) {
                out.write(tempBytes, 0, i);
            }
            return out.toByteArray();
        }
    }

    /**
     * 向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节内容
     * @param append   是否追加
     * @throws IOException
     */
    public static void writeFileByBytes7(String fileName, byte[] bytes, boolean append) throws IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, append))) {
            out.write(bytes);
        }
    }

    /**
     * 从文件开头向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节内容
     * @throws IOException
     */
    public static void writeFileByBytes7(String fileName, byte[] bytes) throws IOException {
        writeFileByBytes7(fileName, bytes, false);
    }

}
