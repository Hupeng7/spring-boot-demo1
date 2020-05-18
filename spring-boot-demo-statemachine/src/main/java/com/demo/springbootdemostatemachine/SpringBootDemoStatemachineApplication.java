package com.demo.springbootdemostatemachine;

import com.demo.springbootdemostatemachine.enums.Events;
import com.demo.springbootdemostatemachine.enums.States;
import com.demo.springbootdemostatemachine.persistdao.OrderStatePersist;
import com.demo.springbootdemostatemachine.persisthandler.PersisStateMachineHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.concurrent.TimeUnit;

/**
 * @since https://blog.csdn.net/weixin_37352094/article/details/90040553?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 */
@SpringBootApplication
public class SpringBootDemoStatemachineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoStatemachineApplication.class, args);
    }

//    @Autowired
//    private StateMachine<States, Events> stateMachine;

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired
    private PersisStateMachineHandler persisStateMachineHandler;

    @Autowired
    private OrderStatePersist orderStatePersist;

    @Override
    public void run(String... args) {
        try {
            // 工厂模式调用  防止并发请求 状态 错乱
//            StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
//            stateMachine.start();
//        stateMachine.sendEvent(Events.WRITE_INFO);
//        stateMachine.sendEvent(Events.PAY);
//        stateMachine.sendEvent(Events.RECEIVE);

            // change("ordeid1", States.APPLY, null); // ??? 必须要有一个event?
//             change("orderId001", States.APPLY, Events.WRITE_INFO);
            change("orderId001", States.UNPAID, Events.PAY);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                change("orderId001", States.UNPAID, Events.WRITE_INFO);
//            }
//        }).start();
//            TimeUnit.SECONDS.sleep(1);
 //           change("orderId001", States.WAITING_FOR_RECEIVE, Events.RECEIVE);
            change("orderId001", States.DONE, Events.RECEIVE);
            orderStatePersist.getState("orderId002");
            orderStatePersist.getState("orderId001");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean change(String orderId, Events event) {
        // OrderObject o = orderRepo.findByOrderIdAndValid(orderId, Constant.DATA_VALID);
        // 参数 state 可以在屏蔽掉； 在数据库中取得  然后传入
        States state = States.WAITING_FOR_RECEIVE;  // o.getState()
        return persisStateMachineHandler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("orderId", orderId).build(), state);
    }

    public boolean change(String orderId, States state, Events event) {
        return persisStateMachineHandler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("orderId", orderId).build(), state);
    }
}
