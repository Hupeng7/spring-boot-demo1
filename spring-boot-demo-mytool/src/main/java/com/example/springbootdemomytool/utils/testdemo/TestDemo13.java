package com.example.springbootdemomytool.utils.testdemo;

/**
 * @ClassName TestDemo13
 * @Description
 * @Author H
 * @Date 2025/6/24 18:37
 * @Version 1.0
 */
public class TestDemo13 {
    public static void main(String[] args) {

        String apiCodeString = ",hz_fraud_score_a1_33,hz_finance_fraud_score_t2_08,hz_fraud_score_a1_09";
        String apiCodeString2 = ",hz_fraud_score_a1_44";
        String[] apiCodeArray = apiCodeString.split(",");

        for (String apiCode : apiCodeArray) {
            System.out.println(apiCode);
        }


    }
}
