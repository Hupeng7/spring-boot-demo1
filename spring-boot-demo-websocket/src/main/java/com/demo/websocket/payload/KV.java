package com.demo.websocket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName KV
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/7 10:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KV {
    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private Object value;
}
