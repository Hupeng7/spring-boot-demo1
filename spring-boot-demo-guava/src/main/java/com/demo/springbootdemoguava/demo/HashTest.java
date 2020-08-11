package com.demo.springbootdemoguava.demo;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ClassName HashTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 18:31
 * @Version 1.0
 */
public class HashTest {

    public static void main(String[] args) throws Exception {
        // 构造散列算法
        HashFunction hashFunction = Hashing.md5();
        // 多种多样
//        Hashing.hmacMd5();
//        Hashing.crc32();
//        Hashing.sha256();

        // 获取HashCode
        HashCode hashCode = hashFunction.newHasher().putBytes("your origin bytes".getBytes(StandardCharsets.UTF_8)).hash();
        String md5Str = getMD5FromJdk("your origin bytes");
        // 多种多样的比较方法
        System.out.println("hashCode.toString() :" + hashCode.toString());
        System.out.println("md5Str : " + md5Str);
        System.out.println(hashCode.toString().equals(md5Str));
        hashCode.asBytes();
        hashCode.asInt();

    }

    private static String getMD5FromJdk(String str) throws Exception {

        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算MD5函数
        md.update(str.getBytes());
        // digest() 最后确定返回md5 hash值，返回值为8 字符串，因为MD5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger 函数则将8位的字符串转换成16位的hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);

    }

}
