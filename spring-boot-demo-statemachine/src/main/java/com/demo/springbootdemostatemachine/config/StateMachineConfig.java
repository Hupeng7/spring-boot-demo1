package com.demo.springbootdemostatemachine.config;

import com.demo.springbootdemostatemachine.actions.SendMessageAction;
import com.demo.springbootdemostatemachine.enums.Events;
import com.demo.springbootdemostatemachine.enums.States;
import com.demo.springbootdemostatemachine.listeners.OrderPersistStateChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.*;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @ClassName StateMachineConfig
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/13 17:06
 * @Version 1.0
 */

@Configuration
//@EnableStateMachine
@EnableStateMachineFactory(contextEvents = false)
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Autowired
    private SendMessageAction sendMessageAction;

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.APPLY)
                //.end(States.DONE)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.APPLY).target(States.UNPAID)  // 指定状态来源和目标
                .event(Events.WRITE_INFO)                    // 指定触发事件
                .action(sendMessageAction)                   //
                .and()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);
    }

    @Autowired
    private OrderPersistStateChangeListener orderPersistStateChangeListener;

// 监听
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        config
//                .withConfiguration()
//                .listener(listener());  // 指定状态机的处理监听器
//    }

//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new StateMachineListenerAdapter<States, Events>() {
//            @Override
//            public void transition(Transition<States, Events> transition) {
//                if (transition.getTarget().getId() == States.APPLY) {
//                    log.info("开始申请，待填信息");
//                    return;
//                }
//
//                if (transition.getSource().getId() == States.APPLY &&
//                        transition.getTarget().getId() == States.UNPAID) {
//                    log.info("信息填写完成");
//                    log.info("订单创建，待支付");
//                    return;
//                }
//
//                if (transition.getSource().getId() == States.UNPAID &&
//                        transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    log.info("用户完成支付，待收货");
//                    return;
//                }
//                if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE &&
//                        transition.getTarget().getId() == States.DONE) {
//                    log.info("用户已收货，订单完成");
//                    return;
//                }
//            }
//        };
//    }

    @Bean(name = "taskExecutor")
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        return taskExecutor;
    }

}
