package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @ClassName CheckoutAdvertisePageStrategy
 * @Description
 * @Author H
 * @Date 2021/9/9 16:24
 * @Version 1.0
 */
@Component
public class CheckoutAdvertisePageStrategy implements GetPointAdvertisePageStrategy {
    @Override
    public String getAdvertisePage(Model model) {
        return "checkout-advertise.jsp";
    }
}
