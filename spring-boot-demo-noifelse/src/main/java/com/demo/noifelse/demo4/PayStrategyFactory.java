package com.demo.noifelse.demo4;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PayStrategyFactory
 * @Description
 * @Author H
 * @Date 2021/2/9 10:03
 * @Version 1.0
 */
public class PayStrategyFactory {
    private static Map<String, IPay> PAY_REGISTERS = new HashMap<>();

    public static void register(String code, IPay iPay) {
        if (null != code && !"".equals(code)) {
            PAY_REGISTERS.put(code, iPay);
        }
    }

    public static IPay get(String code) {
        return PAY_REGISTERS.get(code);
    }

}
