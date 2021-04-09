package com.demo.noifelse.demo7;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MedalServicesFactory
 * @Description
 * @Author H
 * @Date 2021/4/8 18:34
 * @Version 1.0
 */
public class MedalServicesFactory {
    private static final Map<String, IMedalService> map = new HashMap<>();

    static {
        map.put("guard", new GuardMedalServiceImpl());
        map.put("vip", new VipMedalServiceImpl());
        map.put("guest", new GuestMedalServiceImpl());
    }

    public static IMedalService getMedalService(String medalType) {
        return map.get(medalType);
    }


}
