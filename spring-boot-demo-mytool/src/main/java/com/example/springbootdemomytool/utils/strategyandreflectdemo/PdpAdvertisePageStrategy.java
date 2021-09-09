package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @ClassName PdpAdvertisePageStrategy
 * @Description
 * @Author H
 * @Date 2021/9/9 16:14
 * @Version 1.0
 */
@Component
public class PdpAdvertisePageStrategy implements GetPointAdvertisePageStrategy {
    @Override
    public String getAdvertisePage(Model model) {
        return "pdp-advertise.jsp";
    }
}
