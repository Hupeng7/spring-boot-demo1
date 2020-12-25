package com.demo.rabbitmq.demo.pojo;

import com.demo.rabbitmq.demo.common.Constant;
import com.demo.rabbitmq.demo.util.JodaTimeUtil;
import com.demo.rabbitmq.demo.util.JsonUtil;
import lombok.*;

import java.util.Date;

/**
 * @ClassName MsgLog
 * @Description
 * @Author H
 * @Date 2020/12/23 18:50
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsgLog {
    private String msgId;
    private String msg;
    private String exchange;
    private String routingKey;
    private Integer status;
    private Integer tryCount;
    private Date nextTryTime;
    private Date createTime;
    private Date updateTime;

    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
