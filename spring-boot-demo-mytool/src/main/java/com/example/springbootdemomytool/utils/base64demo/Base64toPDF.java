package com.example.springbootdemomytool.utils.base64demo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @ClassName Base64toPDF
 * @Description
 * @Author H
 * @Date 2020/12/24 10:50
 * @Version 1.0
 */
public class Base64toPDF {

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) {
        // 将PDF格式文件转成base64编码
        //String base64String = "aGVsbG8=";
        //String base64String = getPDFBinary(new File("D:\\temp\\pdf\\11.pdf"));
        //
        String base64String = txt2String("D:\\temp\\pdf\\yyf.txt");
        //System.out.println(base64String);

        // 将base64的编码转成PDF格式文件
        base64StringToPDF(base64String, "d:\\temp\\pdf\\hello" + System.currentTimeMillis() + ".pdf");
        // ==============
        // safsaf();
        // outFile(base64String);
        System.out.println("done----------");
    }


    /**
     * @Title: safsaf
     * @Description: TODO
     * @return: void
     */
    private static void safsaf() {
        String s = "akjibuvuvu";
        FileWriter fw = null;
        File f = new File("a.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(s, 0, s.length() - 1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    /**
     * 输出到文件
     */
    public static void outFile(String string) {
        File file = new File("D:/temp/pdf/yyf.txt");
        try (FileOutputStream fop = new FileOutputStream(file)) {
            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = string.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将PDF转换成base64编码 1.使用BufferedInputStream和FileInputStream从File指定的文件中读取内容；
     * 2.然后建立写入到ByteArrayOutputStream底层输出流对象的缓冲输出流BufferedOutputStream
     * 3.底层输出流转换成字节数组，然后由BASE64Encoder的对象对流进行编码
     */
    public static String getPDFBinary(File file) {
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            // 建立读取文件的文件输出流
            fin = new FileInputStream(file);
            // 在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(fin);
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            // 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            // sun公司的API
            return encoder.encodeBuffer(bytes).trim();
            // apache公司的API
            // return Base64.encodeBase64String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fin.close();
                bin.close();
                // 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException
                // IOException
                // baos.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将base64编码转换成PDF       可用
     *
     * @param base64sString 1.使用BASE64Decoder对编码的字符串解码成字节数组
     *                      2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
     *                      3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
     *                      4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
     * @param fileName      文件名称
     */
    public static void base64StringToPDF(String base64sString, String fileName) {
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            // 将base64编码的字符串解码成字节数组
            byte[] bytes = decoder.decodeBuffer(base64sString);
            // apache公司的API
            // byte[] bytes = Base64.decodeBase64(base64sString);
            // 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出的文件
            File file = new File(fileName);
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);
            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param base64string
     * @return
     * @Title: base64StrToInputStream
     * @Description: base64转成InputStream流对象
     * @return: InputStream
     */
    public static InputStream base64StrToInputStream(String base64string) {
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }

    /**
     * 读取txt文件内容    可用
     *
     * @param filePath
     * @return
     */
    public static String txt2String(String filePath) {
        File file = new File(filePath);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;

            while ((s = bufferedReader.readLine()) != null) {
                // 换行
                //stringBuffer.append(System.lineSeparator() + s);
                stringBuffer.append(s);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }


}
