package com.demo.test;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName demo2
 * @Description
 * @Author H
 * @Date 2023/9/4 11:37
 * @Version 1.0
 */
public class demo2 {
    public static void main(String[] args) {
        // 原始明文字符串
        String originalStr = "Hello 你好";

        // 将明文字符串转换为16进制表示
        String hexStr = bytesToHex(originalStr.getBytes(StandardCharsets.UTF_8));
        System.out.println("16进制表示: " + hexStr);


        // 将16进制表示转换回原始字符串
        byte[] bytes = hexToBytes(hexStr);
        String decodedStr = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("解码后的字符串: " + decodedStr);

        // 原编码
        String aaa = "d38d3961d5185987779d62fefebdafa691d05639bdf77ea9cf2f93dd4922714e80c7a94edc1d98e705a89c35022e5cbf067acde086b2d5bb1be691be06a11bed7add3f4dc3237c62";
        //aaa="779d62fefebdafa691d05639bdf77ea9cf2f93dd4922714e80c7a94edc1d98e705a89c35022e5cbf067acde086b2d5bb1be691be06a11bed7add3f4dc3237c62";
        System.out.println("原始-解码后的字符串: " + new String(hexToBytes(aaa),StandardCharsets.UTF_8));
    }

    // 将字节数组转换为16进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }

    // 将16进制字符串转换为字节数组
    private static byte[] hexToBytes(String hexStr) {
        int len = hexStr.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
                    + Character.digit(hexStr.charAt(i + 1), 16));
        }
        return bytes;
    }
}
