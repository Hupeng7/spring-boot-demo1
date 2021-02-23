package com.demo.noifelse.demo5;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PayHandler
 * @Description
 * @Author H
 * @Date 2021/2/9 10:25
 * @Version 1.0
 */
public abstract class PayHandler {

    @Getter
    @Setter
    protected PayHandler next;

    public abstract void pay(String pay);
}
