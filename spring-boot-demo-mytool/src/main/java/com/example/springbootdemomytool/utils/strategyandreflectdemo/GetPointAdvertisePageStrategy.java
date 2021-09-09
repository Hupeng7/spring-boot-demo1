package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.springframework.ui.Model;

public interface GetPointAdvertisePageStrategy {

    /**
     * 得到指定的页面
     *
     * @param
     * @return
     */
    String getAdvertisePage(Model model);
}
