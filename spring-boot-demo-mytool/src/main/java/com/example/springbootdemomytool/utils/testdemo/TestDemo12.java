package com.example.springbootdemomytool.utils.testdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName TestDemo12
 * @Description
 * @Author H
 * @Date 2024/12/6 14:13
 * @Version 1.0
 */
@Slf4j
public class TestDemo12 {

    public static BigDecimal getFirstPeriodT30PaidFee3V2(BigDecimal t0DueFee3, BigDecimal t30PaidInterest, BigDecimal t0DueInterest) {
        if (t0DueInterest.compareTo(BigDecimal.ZERO) <= 0) {
            log.info("t0DueInterest: {}", t0DueInterest);
            return BigDecimal.ZERO;
        }
        BigDecimal t30PaidFee3 = t30PaidInterest.multiply(t0DueFee3).divide(t0DueInterest, 2, BigDecimal.ROUND_HALF_UP);
        return t30PaidFee3;
    }

    public static void main(String[] args) {
        BigDecimal t0DueFee3 = new BigDecimal("0.25");
        BigDecimal t30PaidInterest = new BigDecimal("0.21");
        BigDecimal t0DueInterest1 = new BigDecimal("6.25");
        BigDecimal t0DueInterest = new BigDecimal("-1");
        BigDecimal t30PaidFee3 = getFirstPeriodT30PaidFee3V2(t0DueFee3, t30PaidInterest, t0DueInterest);
        log.info("t30PaidFee3: {}", t30PaidFee3);

        // 计算字符串长度  一个字母，一个汉字，标点符号都算一个字符， 共 50
        String a = "{\"data\":{\"result\":0,\"resultMsg\":\"查询成功无数据\"},\"seqNum";
        String b = "\"resultMsg\":\"查询成功无数据\"}";
        String c = ",\"seqNum";
        String d = "{\"data\":{";
        String e = "\"result\":0,";
        log.info("length: {}", a.length());
        log.info("length b: {}", b.length());
        log.info("length c: {}", c.length());
        log.info("length d: {}", d.length());
        log.info("length e: {}", e.length());

        String mobile = "ac";
        if (StringUtils.isBlank(mobile) || mobile.length() < 11) {
            log.info("mobile:{}", mobile);
        }

    }

}
