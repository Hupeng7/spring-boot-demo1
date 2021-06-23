package com.example.springbootdemomytool;

import org.foo.demo.animal.IShout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ServiceLoader;

/**
 * @ClassName SpiTest
 * @Description
 * @Author H
 * @Date 2021/6/23 17:37
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiTest {

    @Test
    public void test() {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout shout : shouts) {
            shout.shout();
        }
    }

}
