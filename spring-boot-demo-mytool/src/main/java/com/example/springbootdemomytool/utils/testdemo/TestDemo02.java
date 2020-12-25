package com.example.springbootdemomytool.utils.testdemo;


import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName TestDemo02
 * @Description
 * @Author H
 * @Date 2020/12/11 18:03
 * @Version 1.0
 */
public class TestDemo02 {

    public Integer getIntVal() {
        return 10;
    }

    public static void IOUtilsDemo(){

        IOUtils.closeQuietly((InputStream) null);

    }

    public static BigDecimal getFirstPeriodT30PaidFee3(Integer productType, Integer installmentNper, BigDecimal t0_due_fee3, Date dueDate, Date payTime, Date settlementDate) {
        BigDecimal days = BigDecimal.ZERO;
        if (installmentNper == 1) {
            days = BigDecimal.valueOf(DateUtil.differentDaysByMillisecond(payTime, settlementDate));
        } else {
            days = BigDecimal.valueOf(DateUtil.differentDaysByMillisecond(dueDate, settlementDate));
        }
        BigDecimal result = t0_due_fee3.multiply(days).divide(BigDecimal.valueOf(30), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal t0DueFee3Half = t0_due_fee3.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
        if (productType == 1) {
            // @since20201224 算得结果小于0的,返回0
            if (result.compareTo(BigDecimal.ZERO) == -1) {
                return BigDecimal.ZERO;
            }
            return result;
        } else if (productType == 2) {
            BigDecimal result2 = result.add((t0_due_fee3.subtract(result)).divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP));
            // @since20201224 算得结果小于t0_due_fee3的一半，按一半返回
            if (result2.compareTo(t0DueFee3Half) == -1) {
                return t0DueFee3Half;
            }
            return result2;
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static void main(String[] args){
        Date dueDate = DateUtil.parseDate("2020-11-11", "yyyy-MM-dd");
        Date payTime = DateUtil.parseDate("2020-11-11", "yyyy-MM-dd");
        Date settlementDate = DateUtil.parseDate("2020-11-20", "yyyy-MM-dd");
        BigDecimal firstPeriodT30PaidFee3 = getFirstPeriodT30PaidFee3(1, 2, new BigDecimal("30.85"), dueDate, payTime, settlementDate);
        System.out.println("final paid fee3: " + firstPeriodT30PaidFee3);

    }
}
