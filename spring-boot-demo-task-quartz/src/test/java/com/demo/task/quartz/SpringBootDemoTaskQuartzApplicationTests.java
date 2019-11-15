package com.demo.task.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoTaskQuartzApplicationTests {

    @Test
    public void contextLoads() {
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(4);
        System.out.println("&****************************************************&&&&&");
        System.out.println(set.toString());
    }

}
