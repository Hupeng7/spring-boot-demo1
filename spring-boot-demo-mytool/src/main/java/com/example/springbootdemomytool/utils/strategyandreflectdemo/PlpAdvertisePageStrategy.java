package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @ClassName PlpAdvertisePageStrategy
 * @Description
 * @Author H
 * @Date 2021/9/9 16:16
 * @Version 1.0
 */
@Component
public class PlpAdvertisePageStrategy implements GetPointAdvertisePageStrategy {
    @Override
    public String getAdvertisePage(Model model) {
        return "plp-advertise.jsp";
    }
}
