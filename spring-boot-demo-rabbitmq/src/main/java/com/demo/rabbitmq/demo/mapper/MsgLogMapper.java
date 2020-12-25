package com.demo.rabbitmq.demo.mapper;


import com.demo.rabbitmq.demo.pojo.MsgLog;
import com.demo.rabbitmq.demo.service.BatchProcessMapper;

import java.util.List;

public interface MsgLogMapper extends BatchProcessMapper<MsgLog> {

    void insert(MsgLog msgLog);

    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

}
