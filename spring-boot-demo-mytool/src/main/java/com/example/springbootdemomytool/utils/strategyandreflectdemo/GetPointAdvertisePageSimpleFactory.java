package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GetPointAdvertisePageSimpleFactory
 * @Description 生产广告页面的简单工厂
 * @Author H
 * @Date 2021/9/9 16:28
 * @Version 1.0
 */
@Component
public class GetPointAdvertisePageSimpleFactory {
    public static final String PDP_ITEM_ADVERTISE = "PDP_ITEM_ADVERTISE";
    public static final String PLP_ITEM_ADVERTISE = "PLP_ITEM_ADVERTISE";
    public static final String CHECKOUT_ITEM_ADVERTISE = "CHECKOUT_ITEM_ADVERTISE";
    public static final String SHOPPINGCART_ITEM_ADVERTISE = "SHOPPING_ITEM_ADVERTISE";

    private static Map<String, GetPointAdvertisePageStrategy> recommendStrategyMap = new HashMap<>();

    @Autowired
    private CheckoutAdvertisePageStrategy checkoutAdvertisePageStrategy;
    @Autowired
    private PdpAdvertisePageStrategy pdpAdvertisePageStrategy;
    @Autowired
    private PlpAdvertisePageStrategy plpAdvertisePageStrategy;
    @Autowired
    private ShoppingCartAdvertisePageStrategy shoppingCartAdvertisePageStrategy;

    /**
     * 初始化多有的策略类
     */
    protected void init() {
        recommendStrategyMap.put(PDP_ITEM_ADVERTISE, pdpAdvertisePageStrategy);
        recommendStrategyMap.put(PLP_ITEM_ADVERTISE, plpAdvertisePageStrategy);
        recommendStrategyMap.put(CHECKOUT_ITEM_ADVERTISE, checkoutAdvertisePageStrategy);
        recommendStrategyMap.put(SHOPPINGCART_ITEM_ADVERTISE, shoppingCartAdvertisePageStrategy);
    }


    /**
     * 根据pageType 得到指定的处理类
     *
     * @param pageType
     * @return
     */
    public GetPointAdvertisePageStrategy getStrategy(String pageType) {
        if (recommendStrategyMap == null || recommendStrategyMap.size() == 0) {
            init();
        }
        return recommendStrategyMap.get(pageType);
    }

}
