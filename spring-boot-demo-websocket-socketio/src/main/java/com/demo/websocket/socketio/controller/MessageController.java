package com.demo.websocket.socketio.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.demo.websocket.socketio.handler.MessageEventHandler;
import com.demo.websocket.socketio.payload.BroadcastMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * @ClassName MessageController
 * @Description 消息发送Controller
 * @Author Leo
 * @Date 2019/11/26 18:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/send")
@Slf4j
public class MessageController {

    @Autowired
    private MessageEventHandler messageEventHandler;

    @PostMapping("/broadcast")
    public Dict broadcast(@RequestBody BroadcastMessageRequest message) {
        if (isBlank(message)) {
            return Dict.create().set("flag", false).set("code", 400).set("message", "参数为空");
        }
        messageEventHandler.sendToBroadcast(message);
        return Dict.create().set("flag", true).set("code", 200).set("message", "发送成功");
    }

    /**
     * 判断Bean是否为空对象或者空白字符串，空对象表示本身为 null 或者所有属性都为 null
     *
     * @param bean
     * @return
     */
    private boolean isBlank(Object bean) {
        if (null != bean) {
            for (Field field : ReflectUtil.getFields(bean.getClass())) {
                Object fieldValue = ReflectUtil.getFieldValue(bean, field);
                if (null != fieldValue) {
                    if (fieldValue instanceof String && StrUtil.isNotBlank((String) fieldValue)) {
                        return false;
                    } else if (!(fieldValue instanceof String)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
