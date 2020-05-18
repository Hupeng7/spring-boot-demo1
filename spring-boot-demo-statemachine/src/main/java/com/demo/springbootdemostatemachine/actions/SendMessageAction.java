package com.demo.springbootdemostatemachine.actions;

import com.demo.springbootdemostatemachine.enums.Events;
import com.demo.springbootdemostatemachine.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @ClassName SendMessageAction
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/13 17:51
 * @Version 1.0
 */
@Component
public class SendMessageAction implements Action<States, Events> {
    // @Autowired 注入需要操作的

    @Override
    public void execute(StateContext<States, Events> stateContext) {
        // System.out.println(stateContext.toString());
        // messageHeaders={id=a27c0865-fea4-f750-0aca-74ef0af1d311, _sm_id_=9e75adaa-583c-4df6-83c2-42952f8efa93, timestamp=1589364156374}
        String str = stateContext.getMessageHeader("orderId").toString();
        System.out.println("SendMessageAction: , " + str);
    }
}
