package com.example.springbootdemodbserver;

import com.example.springbootdemodbserver.utils.retryutils.SpringRetryDemoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SpringRetryDemoServiceTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 15:39
 * @Version 1.0
 */
public class SpringRetryDemoServiceTest extends SpringBootDemoDbserverApplicationTests {

    @Autowired
    private SpringRetryDemoService springRetryDemoService;

    @Test
    public void retry() {
        int count = springRetryDemoService.retry(-1);
        System.out.println("库存为：" + count);
    }

}
