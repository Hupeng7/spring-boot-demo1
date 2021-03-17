package com.example.springbootdemomytool.utils.shorturldemo;

import java.util.UUID;

/**
 * @ClassName HashUtil
 * @Description
 * https://mp.weixin.qq.com/s/T26eUvxLKd9Z5yNYIX1DIg
 * https://www.jianshu.com/p/7189521b5cfb
 * @Author H
 * @Date 2021/3/17 13:36
 * @Version 1.0
 */
public class HashUtil {

    private static final int c1 = 0xcc9e2d51;
    private static final int c2 = 0x1b873593;
    private static final int r1 = 15;
    private static final int r2 = 13;
    private static final int m = 5;
    private static final int n = 0xe6546b64;

    private static final int DEFAULT_SEED = 0;


    private static int hash32(byte[] key, int seed) {
        int hash = seed;
        final int len = key.length;
        int i = 0;
        int k = 0;
        for (; i + 4 <= len; i += 4) {
            k = ((key[i + 3] & 0xff) << 24)
                    | ((key[i + 2] & 0xff) << 16)
                    | ((key[i + 1] & 0xff) << 8)
                    | (key[i] & 0xff);
            k *= c1;
            k = Integer.rotateLeft(k, r1);
            k *= c2;

            hash ^= k;
            hash = Integer.rotateLeft(hash, r2);
            hash = hash * m + n;
        }

        int k1 = 0;
        switch (len - i) {
            case 3:
                k1 = (key[i + 2] & 0xff) << 16;
            case 2:
                k1 |= (key[i + 1] & 0xff) << 8;
            case 1:
                k1 |= key[i] & 0xff;
                k1 *= c1;
                k1 = Integer.rotateLeft(k1, r1);
                k1 *= c2;
                hash ^= k1;
        }

        hash ^= len;
        hash ^= hash >>> 16;
        hash *= 0x85ebca6b;
        hash ^= hash >>> 13;
        hash *= 0xc2b2ae35;
        hash ^= hash >>> 16;

        return hash;
    }

    public static int hash32(String str) {
        return hash32(str.getBytes(), DEFAULT_SEED);
    }

    /**
     * 10进制转62进制
     *
     * @param num
     * @return
     */
    public static String to62HEX(int num) {
        num = Math.abs(num);
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();
        int remainder;

        while (num > 62 - 1) {
            remainder = Long.valueOf(num % 62).intValue();
            sb.append(chars.charAt(remainder));
            num = num / 62;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        return sb.reverse().toString();
    }

    public static String generate(String originaUrl) {
        return to62HEX(HashUtil.hash32(originaUrl));
    }

    public static void main(String[] args) {

        test1();

        //Hash冲突优化 布隆过滤器

        test2();

    }

    /**
     * 获取短链
     */
    public static void test1() {
        String longUrl = "https://time.geekbang.org/column/article/80850";
        String shortUrl = generate(longUrl);
        System.out.println("short url : " + shortUrl);
    }

    public static void test2() {
        int size = 1000000;
        String[] urls = new String[size];

        for (int i = 0; i < size; i++) {
            urls[i] = "https://time.geekbang.org/column/article/80850" + UUID.randomUUID().toString();
        }
        System.out.println("数据填充完成");

        long l = System.currentTimeMillis();
        for (String s : urls) {
            String shortUrl = generate(s);
            //System.out.println("short url : " + shortUrl);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

}
