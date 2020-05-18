package com.demo.springbootdemostatemachine.listeners;

import com.demo.springbootdemostatemachine.enums.Events;
import com.demo.springbootdemostatemachine.enums.States;
import com.demo.springbootdemostatemachine.persistdao.OrderStatePersist;
import com.demo.springbootdemostatemachine.persisthandler.PersisStateMachineHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderPersistStateChangeListener
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/13 18:44
 * @Version 1.0
 */
@Component
@Slf4j
public class OrderPersistStateChangeListener implements PersisStateMachineHandler.PersistStateChangeListener {
    @Autowired
    private OrderStatePersist orderStatePersist;

    @Override
    public void onPersist(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
        System.out.println("inter on persist...");
        // TODO 持久化到数据库
        if (message != null && message.getHeaders().containsKey("orderId")) {
            String orderId = message.getHeaders().get("orderId", String.class);
            // 持久化 修改数据库
            orderStatePersist.changeState(orderId, state.getId());


        }

    }
}
