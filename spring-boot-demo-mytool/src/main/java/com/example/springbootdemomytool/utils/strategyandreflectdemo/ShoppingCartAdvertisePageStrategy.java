package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @ClassName ShoppingCartAdvertisePageStrategy
 * @Description
 * @Author H
 * @Date 2021/9/9 16:19
 * @Version 1.0
 */
@Component
public class ShoppingCartAdvertisePageStrategy implements GetPointAdvertisePageStrategy {
    @Override
    public String getAdvertisePage(Model model) {
        return "shoppingcart-advertise.jsp";
    }
}
