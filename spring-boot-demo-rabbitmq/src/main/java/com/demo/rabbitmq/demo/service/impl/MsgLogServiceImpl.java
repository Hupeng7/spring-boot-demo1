package com.demo.rabbitmq.demo.service.impl;

import com.demo.rabbitmq.demo.mapper.MsgLogMapper;
import com.demo.rabbitmq.demo.pojo.MsgLog;
import com.demo.rabbitmq.demo.service.MsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName MsgLogServiceImpl
 * @Description
 * @Author H
 * @Date 2020/12/24 17:27
 * @Version 1.0
 */
@Service
public class MsgLogServiceImpl implements MsgLogService {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }
}
