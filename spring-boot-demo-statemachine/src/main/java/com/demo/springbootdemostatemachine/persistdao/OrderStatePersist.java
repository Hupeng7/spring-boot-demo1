package com.demo.springbootdemostatemachine.persistdao;

import com.demo.springbootdemostatemachine.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderStatePersist
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/14 15:22
 * @Version 1.0
 */
@Component
@Slf4j
public class OrderStatePersist {

    private static Map<String, States> orderCacheMap = new HashMap<>();

    public void changeState(String orderId, States state) {
        log.info("【orderCacheMap】 {} before value:{}", orderId, orderCacheMap.get(orderId));
        orderCacheMap.put(orderId, state);
        log.info("【orderCacheMap】 {} after value:{}", orderId, orderCacheMap.get(orderId));
    }

    public States getState(String orderId) {
        States state = orderCacheMap.get(orderId);
        log.info("orderId:{},value {}", orderId, state);
        return state;
    }

}
