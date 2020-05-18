package com.demo.springbootdemostatemachine.persisthandler;

import com.demo.springbootdemostatemachine.enums.Events;
import com.demo.springbootdemostatemachine.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName PersisStateMachineHandler
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/13 18:36
 * @Version 1.0
 */
@Component
@Slf4j
public class PersisStateMachineHandler extends LifecycleObjectSupport {

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    private final PersistingStateChangeInterceptor interceptor = new PersistingStateChangeInterceptor();
    private final CompositePersistStateChangeListener listeners = new CompositePersistStateChangeListener();

    @Autowired
    public PersisStateMachineHandler(PersistStateChangeListener listener) {
        System.out.println("【PersisStateMachineHandler】");
        listeners.register(listener);
    }

    /**
     * 处理entity 事件
     *
     * @param event
     * @param state
     * @return
     */
    public boolean handleEventWithState(Message<Events> event, States state) {
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.addStateMachineInterceptor(interceptor));
        List<StateMachineAccess<States, Events>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        for (StateMachineAccess<States, Events> access : withAllRegions) {
            log.info("handle state: {}", state);
            this.printStateMessage(state);
            access.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null));
        }
        stateMachine.start();
        return stateMachine.sendEvent(event);
    }

    private void printStateMessage(States state) {
        if (States.APPLY.equals(state)) {
            log.info("1开始申请，待填信息");
            return;
        }

        if (States.UNPAID.equals(state)) {
            log.info("2信息填写完成");
            log.info("3订单创建，待支付");
            return;
        }

        if (States.WAITING_FOR_RECEIVE.equals(state)) {
            log.info("4用户完成支付，待收货");
            return;
        }
        if (States.DONE.equals(state)) {
            log.info("5用户已收货，订单完成");
            return;
        }
    }

    private class PersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<States, Events> {
        @Override
        public void preStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
            System.out.println("interceptor ..." + state);
            listeners.onPersist(state, message, transition, stateMachine);
        }
    }

    private class CompositePersistStateChangeListener extends AbstractCompositeListener<PersistStateChangeListener> implements
            PersistStateChangeListener {
        @Override
        public void onPersist(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
            for (Iterator<PersistStateChangeListener> iterator = getListeners().reverse(); iterator.hasNext(); ) {
                PersistStateChangeListener listener = iterator.next();
                System.out.println("handler listener...");
                listener.onPersist(state, message, transition, stateMachine);
            }
        }
    }

    /**
     * 可以通过 addPersistStateChangeListener，增加当前Handler的PersistStateChangeListener。
     * 在状态变化的持久化触发时，会调用相应的实现了PersistStateChangeListener的Listener实例。
     */
    public interface PersistStateChangeListener {
        // 持久化时调用
        void onPersist(State<States, Events> state,
                       Message<Events> message, Transition<States,
                Events> transition,
                       StateMachine<States, Events> stateMachine);
    }

}
