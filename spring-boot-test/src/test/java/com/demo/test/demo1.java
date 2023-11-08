package com.demo.test;

/**
 * @ClassName demo1
 * @Description
 * @Author H
 * @Date 2023/9/4 10:07
 * @Version 1.0
 */
public class demo1 {
    public static void main(String[] args) throws Exception {
        // 原编码
        String aaa = "d38d3961d5185987779d62fefebdafa691d05639bdf77ea9cf2f93dd4922714e80c7a94edc1d98e705a89c35022e5cbf067acde086b2d5bb1be691be06a11bed7add3f4dc3237c62";

        String hexString = "e696b0e7949fe584bf32e4b8aae69c88efbc8ce68c89e9a284e998b2e68ea5e7a78de8aea1e58892e5ba94e68ea5e7a78de79a84e796abe88b97e698afe38082"; // 16进制字符串

        // 将16进制字符串转换为字节数组
        byte[] bytes = hexStringToByteArray(hexString);

        // 将字节数组转换为字符串 "GBK"  "UTF-8"
        String result = new String(bytes,"UTF-8");

        System.out.println("16进制字符串转换后的字符串: " + result);

        System.out.println("原编码-16进制字符串转换后的字符串: " + new String(hexStringToByteArray(aaa)));


        String inputString = "新生儿2个月，按预防接种计划应接种的疫苗是。"; // 普通字符串

        // String inputString = "你好，世界！"; // 包含汉字的字符串
        String charsetName = "UTF-8"; // 字符集名称

        // 将字符串按指定字符集编码为字节数组
        byte[] bytes1 = inputString.getBytes(charsetName);

        // 将字节数组转换为16进制字符串
        String hexString2 = bytesToHex(bytes1);

        System.out.println("转换后的16进制字符串: " + hexString2);

//        // 将普通字符串转换为16进制字符串
//        String hexString1 = stringToHex(inputString);
//
//        System.out.println("转换后的16进制字符串: " + hexString1);




        System.out.println(aaa.equals(hexString));


    }

    // 将字节数组转换为16进制字符串
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // 使用0xFF进行位与运算，将字节转换为无符号整数
            int intValue = b & 0xFF;
            // 将整数转换为16进制字符串，并确保每个字节的表示都是两位
            String hex = Integer.toHexString(intValue);
            if (hex.length() == 1) {
                hexString.append('0'); // 补0
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 将16进制字符串转换为字节数组
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    // 将普通字符串转换为16进制字符串
    public static String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();
        for (char ch : input.toCharArray()) {
            String hex = Integer.toHexString(ch); // 将字符转换为16进制
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
