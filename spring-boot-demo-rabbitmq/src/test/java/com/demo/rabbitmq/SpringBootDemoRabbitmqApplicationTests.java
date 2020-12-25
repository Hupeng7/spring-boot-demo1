package com.demo.rabbitmq;

import com.demo.rabbitmq.entity.User;
import com.demo.rabbitmq.sender.MQSender;
import com.demo.rabbitmq.sender.Sender;
import com.demo.rabbitmq.sender.Sender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootDemoRabbitmqApplicationTests {

    @Autowired
    private Sender sender;
    @Autowired
    private Sender2 sender2;
    @Autowired
    private MQSender mqSender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100000; i++) {
            sender.send(i);
            //sender2.send(i);
            System.out.println("done no is : " + i);
        }
    }

    @Test
    public void sendUser() throws Exception {
        User user = new User(1, "leo");
        sender.send(user);
    }

    @Test
    public void sendTopic() {
        sender.send1();
        sender.send2();
    }

    @Test
    public void mqSend() {
        String msg = "hello spring boot";
        try {
            for (int i = 0; i < 15; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mqSender.send(msg + ":" + i, null);
                //mqSender.send(new User(1, "hello"), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
